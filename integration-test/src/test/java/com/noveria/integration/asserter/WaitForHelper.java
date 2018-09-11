package com.noveria.integration.asserter;

import com.noveria.assertion.exception.WaitUntilAssertionError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.fail;

@Component
@TestPropertySource("classpath:test.properties")
public class WaitForHelper {

    private static Logger logger = LoggerFactory.getLogger(WaitForService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${music.repo.url}")
    String musicRepoUrl;

    @Value("${music.service.url}")
    String musicServiceUrl;

    @Value("${artist.service.url}")
    String artistServiceUrl;

    private static boolean serviceUnavailable = false;

    public void waitForMusicRepository() throws InterruptedException {
        waitForService(musicRepoUrl,"music-repository");
    }

    public void waitForMusicService() throws InterruptedException {
        waitForService(musicServiceUrl,"music-service");
    }

    public void waitForArtistService() throws InterruptedException {
        waitForService(artistServiceUrl,"artist-service");
    }

    private void waitForService(String baseUrl, String name) throws InterruptedException {
        WaitForService waitForEmailService = new WaitForService(baseUrl + "/actuator/info", restTemplate);
        waitForEmailService.setMaxWaitTime(300000);

        if(serviceUnavailable) {
            fail(name+" unavailable");
        }

        logger.info("waiting for {} at {}/actuator/info",name,baseUrl);

        try {
            waitForEmailService.performAssertion();
        }catch (WaitUntilAssertionError wae) {
            serviceUnavailable = true;
            fail(name+" unavailable");
        }
    }
}
