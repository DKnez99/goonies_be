package com.goonsquad.goonies.api.movie.genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Short> {

    Optional<Genre> findByNameIgnoreCase(String name);

}
