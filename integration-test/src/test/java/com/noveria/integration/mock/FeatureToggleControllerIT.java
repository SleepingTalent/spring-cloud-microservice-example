package com.noveria.integration.mock;

package com.vebnet.reflex.featuretoggle.api.controller;

import com.vebnet.reflex.featuretoggle.api.config.TestConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
public class FeatureToggleControllerIT extends BaseApiIT {

    private static final Logger logger = LoggerFactory.getLogger(FeatureToggleControllerIT.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${featuretoggle.url}")
    private String baseUrl;

    private static final String ACTIVE_FEATURE = "TestFeatureOne";
    private static final String ACTIVE_COMPANY_CODE = "activeCompanyCode";

    private static final String INACTIVE_FEATURE = "TestFeatureTwo";
    private static final String INACTIVE_COMPANY_CODE = "inactiveCompanyCode";

    private static final String FEATURE_TOGGLE_API = "featuretoggle-api";

    @Before
    public void setUp() throws Exception {
        mockEurekaServer = initialiseMockEurekaServer();
        mockEurekaServer.addApplication(FEATURE_TOGGLE_API,createRemoteApps(FEATURE_TOGGLE_API,2000));
        mockEurekaServer.start();

        //Wait for service to register
        logger.info("waiting for featuretoggle-api to register with mockEurekaServer...");
        TimeUnit.SECONDS.sleep(30);
    }

    @After
    public void tearDown() throws Exception {
        mockEurekaServer.stop();
    }

    @Test
    @Sql(scripts = "/data/insertFeatureOneActiveForCompanyCode.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/featureCleanUp.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
    public void isFeatureActive_featureFoundAndActive() {
        mockCompanyServiceAPI(ACTIVE_COMPANY_CODE, 1000);

        ResponseEntity<Result> response = restTemplate.getForEntity(getUrl(ACTIVE_COMPANY_CODE,ACTIVE_FEATURE), Result.class);
        logger.info("response received : {}:{}",response.getStatusCode(),response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ACTIVE_COMPANY_CODE, response.getBody().getCompanyCode());
        assertEquals(ACTIVE_FEATURE, response.getBody().getFeatureName());
        assertTrue(response.getBody().isActive());
    }

    @Test
    @Sql(scripts = "/data/insertFeatureOneInactiveForCompanyCode.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "/data/featureCleanUp.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = TransactionMode.ISOLATED))
    public void isFeatureActive_featureFoundAndNotActive() {
        mockCompanyServiceAPI(INACTIVE_COMPANY_CODE, 1001);

        ResponseEntity<Result> response = restTemplate.getForEntity(getUrl(INACTIVE_COMPANY_CODE,INACTIVE_FEATURE), Result.class);
        logger.info("response received : {}:{}",response.getStatusCode(),response.getBody());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(INACTIVE_COMPANY_CODE, response.getBody().getCompanyCode());
        assertEquals(INACTIVE_FEATURE, response.getBody().getFeatureName());
        assertFalse(response.getBody().isActive());
    }

    private String getUrl(String companyCode, String feature) {
        String url = baseUrl + companyCode + "/" + feature;
        logger.info("url: " + url);
        return url;
    }
}
