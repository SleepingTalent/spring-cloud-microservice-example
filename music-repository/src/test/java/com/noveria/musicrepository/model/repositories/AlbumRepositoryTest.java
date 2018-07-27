package com.noveria.musicrepository.model.repositories;

import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = Artist.class)
public class AlbumRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    private Album album;

    @Before
    public void setUp() throws Exception {
        album = new Album();
        album.setName("Be Here Now");
        album.setDescription("3rd Album");
        entityManager.persist(album);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.remove(album);
    }

    @Test
    public void getAlbumById() {
        assertEquals("Be Here Now", albumRepository.findOne(album.getId()).getName());
    }

    @Test
    public void getAlbumByName() {
        assertEquals("Be Here Now", albumRepository.findByName(album.getName()).getName());
    }

}