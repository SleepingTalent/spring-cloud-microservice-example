package com.noveria.musicrepository.model.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrackTest {

    private Track track = new Track();

    @Test
    public void getId() {
        track.setId(32L);
        assertEquals(32L, track.getId().longValue());
    }

    @Test
    public void getName() {
        track.setName("Be Here Now");
        assertEquals("Be Here Now", track.getName());
    }

    @Test
    public void getAlbum() {
        Album album = new Album();
        track.setAlbum(album);
        assertEquals(album, track.getAlbum());
    }
}