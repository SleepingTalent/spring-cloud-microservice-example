package com.noveria.integration;

import com.noveria.integration.application.TestApplication;
import com.noveria.integration.asserter.WaitForHelper;
import com.noveria.integration.asserter.WaitForService;
import com.noveria.integration.response.musicrepo.AlbumResponse;
import com.noveria.integration.response.musicrepo.ArtistResponse;
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
public class MusicServiceIT {

    private static Logger logger = LoggerFactory.getLogger(WaitForService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WaitForHelper waitForHelper;

    @Value("${music.service.url}")
    String musicServiceUrl;

    @Before
    public void before() throws InterruptedException {
        waitForHelper.waitForMusicService();
    }

    @Test
    public void getAlbumsUrl_returns_expected_albums() {
        String albumUrl = musicServiceUrl +"/album";

        logger.info("calling url: {}",albumUrl);

        ResponseEntity<List<AlbumResponse>> getAllAlbumsResponse =
                restTemplate.exchange(albumUrl,HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<AlbumResponse>>() {});

        List<AlbumResponse> albums = getAllAlbumsResponse.getBody();

        assertEquals(2, albums.size());

        assertEquals(1L, albums.get(0).getId().longValue());
        assertEquals("The Joshua Tree", albums.get(0).getName());
        assertEquals("Ground Breaking Album", albums.get(0).getDescription());

        assertEquals(2L, albums.get(1).getId().longValue());
        assertEquals("Achtung Baby", albums.get(1).getName());
        assertEquals("90s Album", albums.get(1).getDescription());
    }

    @Test
    public void getArtistUrl_returns_expected_albums() {
        String artistUrl = musicServiceUrl +"/artist";

        logger.info("calling url: {}",artistUrl);

        ResponseEntity<List<ArtistResponse>> getAllArtistsResponse =
                restTemplate.exchange(artistUrl,HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ArtistResponse>>() {});

        List<ArtistResponse> artists = getAllArtistsResponse.getBody();

        assertEquals(1, artists.size());

        assertEquals(1L, artists.get(0).getId().longValue());
        assertEquals("U2", artists.get(0).getName());
        assertEquals("Irish Rock Band", artists.get(0).getDescription());
    }

}

