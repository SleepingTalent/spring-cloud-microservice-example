package com.noveria.musicrepository.service;

import com.noveria.musicrepository.controller.request.AlbumRequest;
import com.noveria.musicrepository.controller.request.ArtistRequest;
import com.noveria.musicrepository.controller.request.TrackRequest;
import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import com.noveria.musicrepository.model.domain.Track;
import com.noveria.musicrepository.model.repositories.AlbumRepository;
import com.noveria.musicrepository.model.repositories.ArtistRepository;
import com.noveria.musicrepository.model.repositories.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicRepositoryService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> findAllArtists() {
        return artistRepository.findAll();
    }

    public Artist findByName(String name) {
        return artistRepository.findByName(name);
    }

    public List<Album> findAllAlbums() {
        return albumRepository.findAll();
    }

    public List<Track> findAlbumTracks(String albumName) {
        Album album = albumRepository.findByName(albumName);
        return trackRepository.findByAlbum(album);
    }

    public Album findAlbumByName(String name) {
        return albumRepository.findByName(name);
    }

    public Artist createArtist(ArtistRequest artistRequest) {
        Artist artist = new Artist();
        artist.setName(artistRequest.getName());
        artist.setDescription(artistRequest.getDescription());

        artistRepository.save(artist);

        return artist;
    }

    public Album createAlbum(AlbumRequest albumRequest) {
        Album album = new Album();
        album.setName(albumRequest.getName());
        album.setDescription(albumRequest.getDescription());

        albumRepository.save(album);

        return album;
    }

    public Track addTrackToAlbum(TrackRequest trackRequest) throws AlbumNotFoundException {

        Optional<Album> album = albumRepository.findById(
                Long.valueOf(trackRequest.getAlbumId()));

        if(album.isPresent()) {

            Track track = new Track();
            track.setName(trackRequest.getName());
            track.setAlbum(album.get());

            trackRepository.save(track);

            return track;

        }else {
            throw new AlbumNotFoundException();
        }
    }

}