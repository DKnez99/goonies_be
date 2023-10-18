package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.country.Country;
import com.goonsquad.goonies.api.movie.genre.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findAllByGenresContains(Genre genre, Pageable pageable);

    Page<Movie> findAllByOriginCountry(Country country, Pageable pageable);

    boolean existsByTitleAndReleaseDate(String title, Date releaseDate);
}