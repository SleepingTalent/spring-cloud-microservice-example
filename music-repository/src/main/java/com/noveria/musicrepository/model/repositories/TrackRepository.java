package com.noveria.musicrepository.model.repositories;

import com.noveria.musicrepository.model.domain.Album;
import com.noveria.musicrepository.model.domain.Artist;
import com.noveria.musicrepository.model.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByAlbum(Album album);
}

