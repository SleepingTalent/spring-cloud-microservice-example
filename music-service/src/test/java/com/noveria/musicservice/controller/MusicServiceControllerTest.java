package com.noveria.musicservice.controller;

import com.noveria.musicservice.controller.request.AlbumRequest;
import com.noveria.musicservice.controller.request.ArtistRequest;
import com.noveria.musicservice.service.MusicService;
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
@SpringBootTest(classes = MusicServiceController.class)
@ActiveProfiles("test")
public class MusicServiceControllerTest {

    @Autowired
    private MusicServiceController musicServiceController;

    @MockBean
    private MusicService musicService;

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
        when(musicService.findAllAlbums()).thenReturn(allAlbums);
        musicServiceController.findAllAlbums();
        verify(musicService, times(1)).findAllAlbums();
    }

    @Test
    public void findAllArtists() {
        when(musicService.findAllArtists()).thenReturn(allArtists);
        musicServiceController.findAllArtists();
        verify(musicService, times(1)).findAllArtists();
    }
}
