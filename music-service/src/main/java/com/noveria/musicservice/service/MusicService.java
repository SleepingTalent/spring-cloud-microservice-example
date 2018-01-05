package com.noveria.musicservice.service;

import com.noveria.musicservice.client.MusicRepositoryClient;
import com.noveria.musicservice.controller.request.AlbumRequest;
import com.noveria.musicservice.controller.request.ArtistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    MusicRepositoryClient musicRepository;

    public List<AlbumRequest> findAllAlbums() {
        return musicRepository.findAllAlbums();
    }

    public List<ArtistRequest> findAllArtists() {
        return musicRepository.findAllArtists();
    }
}