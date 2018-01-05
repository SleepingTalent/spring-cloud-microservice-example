package com.noveria.musicservice.client;

import com.noveria.musicservice.controller.request.AlbumRequest;
import com.noveria.musicservice.controller.request.ArtistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MusicRepositoryClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${music.repository.url}")
    private String musicRepoUrl;

    public List<AlbumRequest> findAllAlbums() {
        ResponseEntity<List<AlbumRequest>> getAllAlbumsResponse =
                restTemplate.exchange(musicRepoUrl + "/album", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<AlbumRequest>>() {
                        });

        return getAllAlbumsResponse.getBody();

    }

    public List<ArtistRequest> findAllArtists() {
        ResponseEntity<List<ArtistRequest>> getAllArtistsResponse =
                restTemplate.exchange(musicRepoUrl + "/artist", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<ArtistRequest>>() {
                        });

        return getAllArtistsResponse.getBody();
    }

}
