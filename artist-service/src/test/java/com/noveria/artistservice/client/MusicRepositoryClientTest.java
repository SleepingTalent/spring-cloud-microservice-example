package com.noveria.artistservice.client;

import com.noveria.artistservice.config.ConfigurationForTest;
import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MusicRepositoryClient.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@Import(ConfigurationForTest.class)
public class MusicRepositoryClientTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicRepositoryClient musicRepositoryClient;

    //@Autowired
    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        server = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testFindAllAlbums() throws Exception {

        server.expect(requestTo("http://test-music-repository/music-repository/album"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getAlbumJson(), MediaType.APPLICATION_JSON));

        List<AlbumRequest> albums = musicRepositoryClient.findAllAlbums();

        server.verify();

        assertEquals(1,albums.size());
        assertEquals("100",albums.get(0).getId());
        assertEquals("Test Album",albums.get(0).getName());
        assertEquals("Rock",albums.get(0).getDescription());
    }

    @Test
    public void testFindAllArtists() throws Exception {
        server.expect(requestTo("http://test-music-repository/music-repository/artist"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getArtistJson(), MediaType.APPLICATION_JSON));

        List<ArtistRequest> artist = musicRepositoryClient.findAllArtists();

        server.verify();

        assertEquals(1,artist.size());
        assertEquals("200",artist.get(0).getId());
        assertEquals("Test Band",artist.get(0).getName());
        assertEquals("4 Piece",artist.get(0).getDescription());
    }

    private String getAlbumJson() {
        return "[\n" +
                "  {\n" +
                "    \"description\": \"Rock\",\n" +
                "    \"id\": 100,\n" +
                "    \"name\": \"Test Album\"\n" +
                "  }\n" +
                "]";
    }

    private String getArtistJson() {
        return "[\n" +
                "  {\n" +
                "    \"description\": \"4 Piece\",\n" +
                "    \"id\": 200,\n" +
                "    \"name\": \"Test Band\"\n" +
                "  }\n" +
                "]";
    }
}