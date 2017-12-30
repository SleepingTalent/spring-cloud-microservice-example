package com.noveria.musicservice.client;

import com.noveria.musicservice.controller.request.AlbumRequest;
import com.noveria.musicservice.controller.request.ArtistRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("music-repository")
public interface MusicRepository {

    @RequestMapping(value = "/album", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<AlbumRequest> findAllAlbums();

    @RequestMapping(value = "/artist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<ArtistRequest> findAllArtists();

}
