package com.noveria.musicrepository.model.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArtistTest {

    private Artist artist = new Artist();

    @Test
    public void getId() {
        artist.setId(32L);
        assertEquals(32L, artist.getId().longValue());
    }

    @Test
    public void getName() {
        artist.setName("Oasis");
        assertEquals("Oasis", artist.getName());
    }

    @Test
    public void getDescription() {
        artist.setDescription("Rock Band");
        assertEquals("Rock Band", artist.getDescription());
    }
}