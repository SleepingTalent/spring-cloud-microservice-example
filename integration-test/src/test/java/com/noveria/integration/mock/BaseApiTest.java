package com.noveria.integration.mock;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.LeaseInfo;
import com.netflix.discovery.shared.Application;
import com.vebnet.reflex.featuretoggle.api.mock.MockEurekaServer;
import org.junit.Rule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseApiIT {

    private static final String APPLICATION_JSON = "application/json";

    private static final String REMOTE_REGION_INSTANCE_1_HOSTNAME = "localhost";
    private static final String REMOTE_ZONE = "us-east-1c";

    private static final String COMPANY_SERVICE_API = "company-service-api";

    protected MockEurekaServer mockEurekaServer;

    private final int eurekaPort = 2001;
    private final int companyApiPort = 2002;

    @Rule
    public WireMockRule serviceMock = new WireMockRule(new WireMockConfiguration().port(companyApiPort)
    //        .notifier(new Slf4jNotifier(true)) //uncomment to display wiremock output in console.
    );

    protected void mockCompanyServiceAPI(String activeCompanyCode, int id) {
        stubFor(get(urlEqualTo("/company-service-api/company/" + activeCompanyCode))
                .willReturn(aResponse().withStatus(200)
                        .withHeader("Content-Type", APPLICATION_JSON)
                        .withBody("{\"id\": \""+id+"\", \"externalCode\": \""+activeCompanyCode+"\"}")));
    }


    protected MockEurekaServer initialiseMockEurekaServer() {
        MockEurekaServer mockEurekaServer = new MockEurekaServer(eurekaPort/* use ephemeral */);
        mockEurekaServer.addApplication(COMPANY_SERVICE_API,createRemoteApps(COMPANY_SERVICE_API,companyApiPort));
        return  mockEurekaServer;
    }

    protected Application createRemoteApps(String applicationName, int port) {
        Application myapp = new Application(applicationName);
        InstanceInfo instanceInfo = createRemoteInstance(applicationName, REMOTE_REGION_INSTANCE_1_HOSTNAME, port);
        myapp.addInstance(instanceInfo);
        return myapp;
    }

    private InstanceInfo createRemoteInstance(String applicationName, String instanceHostName, int port) {
        InstanceInfo.Builder instanceBuilder = InstanceInfo.Builder.newBuilder();
        instanceBuilder.setAppName(applicationName);
        instanceBuilder.setHostName(instanceHostName);
        instanceBuilder.setIPAddr(instanceHostName);
        instanceBuilder.setInstanceId(instanceHostName+":"+applicationName+":"+port);
        instanceBuilder.setVIPAddress(applicationName);
        instanceBuilder.setPort(port);
        instanceBuilder.setDataCenterInfo(MockEurekaServer.getAmazonInfo(REMOTE_ZONE, instanceHostName));
        instanceBuilder.setLeaseInfo(LeaseInfo.Builder.newBuilder().build());
        return instanceBuilder.build();
    }

}
