package com.noveria.integration.asserter;

import com.noveria.assertion.asserter.WaitUntilAsserter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class WaitForService extends WaitUntilAsserter {

    private static Logger logger = LoggerFactory.getLogger(WaitForService.class);

    private String url;
    private RestTemplate restTemplate;

    public WaitForService(String url, RestTemplate restTemplate) {
        super();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    protected boolean execute() {
        try {

            logger.info("waiting for service at url : {}",url);
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if(response.getStatusCode().is2xxSuccessful()) {
                return true;
            }else {
                return false;
            }

        }catch (ResourceAccessException ex) {
            return false;
        }catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            return false;
        }
    }

    @Override
    protected String getTaskName() {
        return "WaitForService";
    }

    @Override
    protected String getFailureMessage() {
        return url+" not available";
    }
}
