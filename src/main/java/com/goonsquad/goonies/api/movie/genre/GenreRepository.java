package com.goonsquad.goonies.api.movie.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Short> {

    Optional<Genre> findByNameIgnoreCase(String name);

}
