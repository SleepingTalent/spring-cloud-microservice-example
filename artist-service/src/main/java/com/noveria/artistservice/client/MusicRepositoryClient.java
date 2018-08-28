package com.noveria.artistservice.client;

import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "music-repository")
public interface MusicRepositoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "music-repository/album", consumes = "application/json")
    List<AlbumRequest> findAllAlbums();

    @RequestMapping(method = RequestMethod.GET, value = "music-repository/artist", consumes = "application/json")
    List<ArtistRequest> findAllArtists();
}
