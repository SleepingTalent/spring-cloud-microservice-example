package com.noveria.artistservice.service;

import com.noveria.artistservice.client.MusicRepositoryClient;
import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtistService.class)
@ActiveProfiles("test")
public class ArtistServiceTest {

    @Autowired
    private ArtistService artistService;

    @MockBean
    private MusicRepositoryClient musicRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findAllArtists() {
        List<ArtistRequest> allArtists = new ArrayList<>();
        allArtists.add(new ArtistRequest());

        when(musicRepository.findAllArtists()).thenReturn(allArtists);

        assertEquals(1, artistService.findAllArtists().size());
    }

    @Test
    public void findAllAlbums() {
        List<AlbumRequest> allAlbums = new ArrayList<>();
        allAlbums.add(new AlbumRequest());

        when(musicRepository.findAllAlbums()).thenReturn(allAlbums);

        assertEquals(1, artistService.findAllAlbums().size());
    }


}