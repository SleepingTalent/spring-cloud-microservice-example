package com.noveria.artistservice.controller;

import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import com.noveria.artistservice.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class ArtistServiceController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    ArtistService artistService;

    @RequestMapping(value = "/album", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AlbumRequest> findAllAlbums() {
        logger.info("findAllAlbums() invoked");
        List<AlbumRequest> albumList = artistService.findAllAlbums();
        logger.info("findAllAlbums() found: " + albumList.size());

        return albumList;
    }

    @RequestMapping(value = "/artist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArtistRequest> findAllArtists() {
        logger.info("findAllArtists() invoked");
        List<ArtistRequest> artistList = artistService.findAllArtists();
        logger.info("findAllArtists() found: " + artistList.size());

        return artistList;
    }

}
