package com.noveria.musicrepository.controller;

import com.noveria.musicrepository.controller.request.AlbumRequest;
import com.noveria.musicrepository.controller.request.ArtistRequest;
import com.noveria.musicrepository.controller.request.TrackRequest;
import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import com.noveria.musicrepository.model.domain.Track;
import com.noveria.musicrepository.service.AlbumNotFoundException;
import com.noveria.musicrepository.service.MusicRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
public class MusicRepositoryController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    MusicRepositoryService musicRepositoryService;

    @RequestMapping(value = "/album", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Album> findAllAlbums() {
        logger.info("findAllAlbums() invoked");
        List<Album> albumList = musicRepositoryService.findAllAlbums();
        logger.info("findAllAlbums() found: " + albumList.size());

        return albumList;
    }

    @RequestMapping(value = "/album", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody AlbumRequest albumRequest) {
        logger.info("createAlbum(" + albumRequest + ") invoked");
        return musicRepositoryService.createAlbum(albumRequest);
    }

    @RequestMapping(value = "/artist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Artist> findAllArtists() {
        logger.info("findAllArtists() invoked");
        List<Artist> artistList = musicRepositoryService.findAllArtists();
        logger.info("findAllArtists() found: " + artistList.size());

        return artistList;
    }

    @RequestMapping(value = "/artist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody ArtistRequest artistRequest) {
        logger.info("createArtist(" + artistRequest + ") invoked");
        return musicRepositoryService.createArtist(artistRequest);
    }

    @RequestMapping(value = "/track", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Track addTrackToAlbum(@RequestBody TrackRequest trackRequest) throws AlbumNotFoundException {
        logger.info("addTrackToAlbum(" + trackRequest + ") invoked");
        return musicRepositoryService.addTrackToAlbum(trackRequest);
    }

}
