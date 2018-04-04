package com.noveria.artistservice.service;

import com.noveria.artistservice.client.MusicRepositoryClient;
import com.noveria.artistservice.controller.request.AlbumRequest;
import com.noveria.artistservice.controller.request.ArtistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    MusicRepositoryClient musicRepository;

    public List<AlbumRequest> findAllAlbums() {
            return musicRepository.findAllAlbums();
    }

    public List<ArtistRequest> findAllArtists() {
        return musicRepository.findAllArtists();
    }
}