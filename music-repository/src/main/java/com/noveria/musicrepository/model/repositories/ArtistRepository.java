package com.noveria.musicrepository.model.repositories;

import com.noveria.musicrepository.model.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(String name);
}

