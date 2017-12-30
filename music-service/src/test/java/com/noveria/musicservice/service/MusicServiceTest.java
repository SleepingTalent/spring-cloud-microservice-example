package com.noveria.musicservice.service;

import com.noveria.musicservice.client.MusicRepository;
import com.noveria.musicservice.controller.request.AlbumRequest;
import com.noveria.musicservice.controller.request.ArtistRequest;
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
@SpringBootTest(classes = MusicService.class)
@ActiveProfiles("test")
public class MusicServiceTest {

    @Autowired
    private MusicService musicService;

    @MockBean
    private MusicRepository musicRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findAllArtists() {
        List<ArtistRequest> allArtists = new ArrayList<>();
        allArtists.add(new ArtistRequest());

        when(musicRepository.findAllArtists()).thenReturn(allArtists);

        assertEquals(1,musicService.findAllArtists().size());
    }

    @Test
    public void findAllAlbums() {
        List<AlbumRequest> allAlbums = new ArrayList<>();
        allAlbums.add(new AlbumRequest());

        when(musicRepository.findAllAlbums()).thenReturn(allAlbums);

        assertEquals(1,musicService.findAllAlbums().size());
    }


}