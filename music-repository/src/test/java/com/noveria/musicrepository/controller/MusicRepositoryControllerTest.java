package com.noveria.musicrepository.controller;

import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import com.noveria.musicrepository.service.MusicRepositoryService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MusicRepositoryController.class)
@EntityScan("com.noveria.musicrepository.model.domain")
@ActiveProfiles("test")
public class MusicRepositoryControllerTest {

    @Autowired
    private MusicRepositoryController musicRepositoryController;

    @MockBean
    private MusicRepositoryService musicRepositoryService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private List<Album> allAlbums;

    private List<Artist> allArtists;

    @Before
    public void setUp() throws Exception {
        allAlbums = new ArrayList<>();
        allAlbums.add(new Album());

        allArtists = new ArrayList<>();
        allArtists.add(new Artist());
    }

    @Test
    public void findAllAlbums() {
        when(musicRepositoryService.findAllAlbums()).thenReturn(allAlbums);
        musicRepositoryController.findAllAlbums();
        verify(musicRepositoryService, times(1)).findAllAlbums();
    }

    @Test
    public void findAllArtists() {
        when(musicRepositoryService.findAllArtists()).thenReturn(allArtists);
        musicRepositoryController.findAllArtists();
        verify(musicRepositoryService, times(1)).findAllArtists();
    }
}
