package com.noveria.artistservice.client;

import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@FeignClient(value = "music-repository")
public interface MusicRepositoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "music-repository/album", consumes = "application/json")
    List<AlbumRequest> findAllAlbums();

    @RequestMapping(method = RequestMethod.GET, value = "music-repository/artist", consumes = "application/json")
    List<ArtistRequest> findAllArtists();
}
