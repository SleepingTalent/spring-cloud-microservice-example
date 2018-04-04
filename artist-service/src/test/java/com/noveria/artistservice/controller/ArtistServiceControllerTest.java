package com.noveria.artistservice.controller;

import com.noveria.artistservice.controller.ArtistServiceController;
import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import com.noveria.artistservice.service.ArtistService;
import org.junit.Before;
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

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArtistServiceController.class)
@ActiveProfiles("test")
public class ArtistServiceControllerTest {

    @Autowired
    private ArtistServiceController artistServiceController;

    @MockBean
    private ArtistService artistService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private List<AlbumRequest> allAlbums;

    private List<ArtistRequest> allArtists;

    @Before
    public void setUp() throws Exception {
        allAlbums = new ArrayList<>();
        allAlbums.add(new AlbumRequest());

        allArtists = new ArrayList<>();
        allArtists.add(new ArtistRequest());
    }

    @Test
    public void findAllAlbums() {
        when(artistService.findAllAlbums()).thenReturn(allAlbums);
        artistServiceController.findAllAlbums();
        verify(artistService, times(1)).findAllAlbums();
    }

    @Test
    public void findAllArtists() {
        when(artistService.findAllArtists()).thenReturn(allArtists);
        artistServiceController.findAllArtists();
        verify(artistService, times(1)).findAllArtists();
    }
}
