package com.noveria.musicrepository.model.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlbumTest {

    private Album album = new Album();

    @Test
    public void getId() {
        album.setId(32L);
        assertEquals(32L, album.getId().longValue());
    }

    @Test
    public void getName() {
        album.setName("Be Here Now");
        assertEquals("Be Here Now", album.getName());
    }

    @Test
    public void getDescription() {
        album.setDescription("3rd Album");
        assertEquals("3rd Album", album.getDescription());
    }
}