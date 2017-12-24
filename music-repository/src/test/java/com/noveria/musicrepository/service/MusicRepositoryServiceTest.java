package com.noveria.musicrepository.service;

import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import com.noveria.musicrepository.model.repositories.AlbumRepository;
import com.noveria.musicrepository.model.repositories.ArtistRepository;
import com.noveria.musicrepository.model.repositories.TrackRepository;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MusicRepositoryService.class)
@EntityScan("com.noveria.musicrepository.model.domain")
@ActiveProfiles("test")
public class MusicRepositoryServiceTest {

    @Autowired
    private MusicRepositoryService musicRepositoryService;

    @MockBean
    ArtistRepository artistRepository;

    @MockBean
    AlbumRepository albumRepository;

    @MockBean
    TrackRepository trackRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void findAllArtists() {
        List<Artist> allArtists = new ArrayList<>();
        allArtists.add(new Artist());

        when(artistRepository.findAll()).thenReturn(allArtists);

        assertEquals(1,musicRepositoryService.findAllArtists().size());
    }

    @Test
    public void findAllAlbums() {
        List<Album> allAlbums = new ArrayList<>();
        allAlbums.add(new Album());

        when(albumRepository.findAll()).thenReturn(allAlbums);

        assertEquals(1,musicRepositoryService.findAllAlbums().size());
    }


}