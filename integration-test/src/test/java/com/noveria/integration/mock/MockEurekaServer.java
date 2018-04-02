package com.noveria.integration.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.netflix.appinfo.AmazonInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.converters.jackson.EurekaJsonJacksonCodec;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.*;
import org.junit.Assert;
import org.junit.rules.ExternalResource;
import org.mortbay.jetty.Request;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockEurekaServer extends ExternalResource {

    private static final Logger logger = LoggerFactory.getLogger(MockEurekaServer.class);

    public static final String EUREKA_API_BASE_PATH = "/eureka/v2/";

    private final Map<String, Application> applicationMap = new HashMap<>();

    private final Server server;
    private int port;

    public MockEurekaServer(int port) {
        logger.debug("setting up mockEurekaServer...");

        ServletHandler handler = new AppsResourceHandler("company-service-api");
        EurekaServerConfig serverConfig = new DefaultEurekaServerConfig();

        EurekaServerContext serverContext = mock(EurekaServerContext.class);
        when(serverContext.getServerConfig()).thenReturn(serverConfig);

        handler.addFilterWithMapping(ServerRequestAuthFilter.class, "/*", 1).setFilter(new ServerRequestAuthFilter(serverContext));
        handler.addFilterWithMapping(RateLimitingFilter.class, "/*", 1).setFilter(new RateLimitingFilter(serverContext));

        server = new Server(port);
        server.addHandler(handler);
    }

    public void addApplication(String applicationName, Application application) {
        applicationMap.put(applicationName, application);
        logger.debug("mockEurekaServer updated applications map :{}", stringifyAppMap(applicationMap));
    }

    @Override
    protected void before() throws Throwable {
        start();
    }

    @Override
    protected void after() {
        try {
            stop();
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    public void start() throws Exception {
        logger.info("starting mockEurekaServer...");
        server.start();
        port = server.getConnectors()[0].getLocalPort();
        logger.info("mockEurekaServer started on port : {}",port);
    }

    public void stop() throws Exception {
        logger.info("stopping mockEurekaServer...");
        server.stop();
        logger.info("mockEurekaServer stopped");
        applicationMap.clear();
    }

    public int getPort() {
        return port;
    }

    private static String stringifyAppMap(Map<String, Application> applicationMap) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");

        for (Map.Entry<String, Application> entry : applicationMap.entrySet()) {
            String entryAsString = String.format("{ name : %s , instance count: %d }", entry.getKey(), entry.getValue().getInstances().size());
            builder.append(entryAsString);
            builder.append("\n");
        }

        return builder.toString();
    }

    public static AmazonInfo getAmazonInfo(String availabilityZone, String instanceHostName) {
        AmazonInfo.Builder azBuilder = AmazonInfo.Builder.newBuilder();
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.availabilityZone, null == availabilityZone ? "us-east-1a" : availabilityZone);
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.instanceId, instanceHostName);
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.amiId, "XXX");
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.instanceType, "XXX");
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.localIpv4, "XXX");
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.publicIpv4, "XXX");
        azBuilder.addMetadata(AmazonInfo.MetaDataKey.publicHostname, instanceHostName);
        return azBuilder.build();
    }

    private class AppsResourceHandler extends ServletHandler {

        private String wireMockServiceName;

        AppsResourceHandler(String wireMockServiceName) {
            this.wireMockServiceName = wireMockServiceName;
        }

        @Override
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {

            String pathInfo = request.getPathInfo();
            outputRequestDetails(request, pathInfo);

            boolean handled = false;

            if (null != pathInfo && pathInfo.startsWith("")) {

                pathInfo = pathInfo.substring(EUREKA_API_BASE_PATH.length());
                logger.debug("extracted pathInfo {}",pathInfo);

                if(pathInfo.equals("apps/")) {
                    logger.debug("retrieving all apps {}",pathInfo);

                    Applications apps = new Applications();
                    apps.addApplication(applicationMap.get(wireMockServiceName));
                    apps.setAppsHashCode(apps.getReconcileHashCode());
                    sendOkResponseWithContent((Request) request, response, toJson(apps));
                    handled = true;
                } else if (pathInfo.startsWith("apps")) {
                    logger.debug("retrieving apps {}",pathInfo);
                    handled = returnApplicationResponse(applicationMap,pathInfo,request,response);
                }
            }

            if (!handled) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND,"request path: " + pathInfo + " not supported by eureka resource mock.");
            }
        }

        private boolean returnApplicationResponse(Map<String, Application> applicationMap, String pathInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {
            int firstIndex = pathInfo.indexOf("/");
            int secondIndex =  pathInfo.indexOf("/",firstIndex+1);

            if(secondIndex == -1) {
                String applicationName = pathInfo.substring(firstIndex+1);
                logger.debug("retrieving application {}",applicationName);

                Application application = applicationMap.get(applicationName.toLowerCase());
                sendOkResponseWithContent((Request) request, response, toJson(application));
                return true;
            }else {
                String applicationName = pathInfo.substring(firstIndex+1,secondIndex);
                String instanceString = pathInfo.substring(secondIndex+1);
                String port = instanceString.substring(instanceString.lastIndexOf(":")+1);
                String instanceId = "localhost:"+applicationName.toLowerCase()+":"+port;

                logger.debug("retrieving application {} instance {}",applicationName,instanceId);

                Application application = applicationMap.get(applicationName.toLowerCase());
                InstanceInfo instanceInfo = application.getByInstanceId(instanceId);
                sendOkResponseWithContent((Request) request, response, toJson(instanceInfo));
                return true;
            }
        }

        private void outputRequestDetails(HttpServletRequest request, String pathInfo) {
            if(request.getMethod().equals("PUT")) {
                logger.debug("PUT heartbeat received request on path :{}", pathInfo);
            }else if (request.getMethod().equals("GET")) {
                logger.debug("GET request on path :{}",pathInfo);
             }else if (request.getMethod().equals("POST")) {
                logger.debug("POST registering application on path :{}", pathInfo);
             }  else {
                logger.debug("{} received request on path :{}", request.getMethod(),pathInfo);
            }
        }

        private void sendOkResponseWithContent(Request request, HttpServletResponse response, String content) throws IOException {
            response.setContentType("application/json; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().write(content.getBytes("UTF-8"));
            response.getOutputStream().flush();

            request.setHandled(true);

            logger.debug("sent response for request path: {}",request.getPathInfo());
            logger.debug("sent response content : {}",content);
        }
    }

    private String toJson(Applications apps) throws IOException {
        ObjectMapper objectMapper = new EurekaJsonJacksonCodec().getObjectMapper(Applications.class);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(apps);
    }

    private String toJson(Application app) throws IOException {
        ObjectMapper objectMapper = new EurekaJsonJacksonCodec().getObjectMapper(Application.class);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(app);
    }

    private String toJson(InstanceInfo instanceInfo) throws IOException {
        ObjectMapper objectMapper = new EurekaJsonJacksonCodec().getObjectMapper(InstanceInfo.class);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(instanceInfo);
    }
}
