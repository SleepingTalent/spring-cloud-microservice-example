package com.noveria.musicrepository.controller.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlbumRequestTest {

    private AlbumRequest albumRequest;

    @Before
    public void setUp() throws Exception {
        albumRequest = new AlbumRequest();
        albumRequest.setId("23");
        albumRequest.setName("Be Here Now");
        albumRequest.setDescription("3rd Album");
    }

    @Test
    public void getId() throws Exception {
        assertEquals("23", albumRequest.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Be Here Now", albumRequest.getName());
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals("3rd Album", albumRequest.getDescription());
    }

}