package com.noveria.integration;

import com.noveria.integration.application.TestApplication;
import com.noveria.integration.asserter.WaitForHelper;
import com.noveria.integration.asserter.WaitForService;
import com.noveria.integration.response.musicrepo.AlbumResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

        ResponseEntity<List<AlbumResponse>> getAllAlbumsResponse =
                restTemplate.exchange(albumUrl,HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<AlbumResponse>>() {});

        List<AlbumResponse> albums = getAllAlbumsResponse.getBody();
        assertEquals(1, albums.size());
        assertEquals(1L, albums.get(0).getId().longValue());
        assertEquals("The Joshua Tree", albums.get(0).getName());
        assertEquals("Ground Breaking Album", albums.get(0).getDescription());
    }

}

