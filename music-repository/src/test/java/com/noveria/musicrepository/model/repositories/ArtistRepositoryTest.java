package com.noveria.musicrepository.model.repositories;

import com.noveria.musicrepository.model.domain.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = Artist.class)
public class ArtistRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    private Artist artist;

    @Before
    public void setUp() throws Exception {
        artist = new Artist();
        artist.setName("Oasis");
        artist.setDescription("Rock Band");
        entityManager.persist(artist);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.remove(artist);
    }

    @Test
    public void getArtistById() {
        Optional<Artist> foundArtist = artistRepository.findById(artist.getId());
        assertTrue(foundArtist.isPresent());
        assertEquals("Oasis", foundArtist.get().getName());
    }

    @Test
    public void getArtistByName() {
        assertEquals("Oasis", artistRepository.findByName(artist.getName()).getName());
    }

}