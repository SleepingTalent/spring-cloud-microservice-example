package com.noveria.musicrepository.model.repositories;

import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
        Optional<Album> foundAlbum = albumRepository.findById(album.getId());
        assertTrue(foundAlbum.isPresent());
        assertEquals("Be Here Now", foundAlbum.get().getName());
    }

    @Test
    public void getAlbumByName() {
        assertEquals("Be Here Now", albumRepository.findByName(album.getName()).getName());
    }

}