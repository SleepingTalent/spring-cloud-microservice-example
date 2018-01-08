package com.noveria.musicservice.controller.request;

import com.noveria.artistservice.controller.request.ArtistRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArtistRequestTest {

    private ArtistRequest artistRequest;

    @Before
    public void setUp() throws Exception {
        artistRequest = new ArtistRequest();
        artistRequest.setId("29");
        artistRequest.setName("Oasis");
    }

    @Test
    public void getId() throws Exception {
        assertEquals("29", artistRequest.getId());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Oasis", artistRequest.getName());
    }
}