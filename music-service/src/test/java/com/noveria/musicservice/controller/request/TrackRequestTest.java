package com.noveria.musicservice.controller.request;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrackRequestTest {

    private TrackRequest trackRequest;

    @Before
    public void setUp() throws Exception {
        trackRequest = new TrackRequest();
        trackRequest.setId("747");
        trackRequest.setName("Do You Know?");
        trackRequest.setAlbumId("21");
    }

    @Test
    public void getId() throws Exception {
        assertEquals("747", trackRequest.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Do You Know?", trackRequest.getName());
    }

}