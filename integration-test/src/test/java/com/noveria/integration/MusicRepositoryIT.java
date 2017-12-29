package com.noveria.integration;

import com.noveria.integration.application.TestApplication;
import com.noveria.integration.asserter.WaitForHelper;
import com.noveria.integration.asserter.WaitForService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
@TestPropertySource("classpath:test.properties")
public class MusicRepositoryIT {

    private static Logger logger = LoggerFactory.getLogger(WaitForService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WaitForHelper waitForHelper;

    @Value("${music.repo.url}")
    String musicRepoUrl;

    @Before
    public void before() throws InterruptedException {
        waitForHelper.waitForMusicRepository();
    }

    @Test
    public void getAlbumsUrl_returns_expected_albums() {
        String albumUrl = musicRepoUrl+"/album";

        logger.info("calling url: {}",albumUrl);

       String response = restTemplate.getForObject(albumUrl,String.class);
       assertEquals("[{\"id\":1,\"name\":\"The Joshua Tree\",\"description\":\"Ground Breaking Album\"}]",
               response);
    }

}

