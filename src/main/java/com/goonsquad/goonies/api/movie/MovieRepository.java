package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.country.Country;
import com.goonsquad.goonies.api.movie.genre.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findAllByGenresContainsOrderByGooniesRatingDesc(Genre genre, Pageable pageable);

    Page<Movie> findAllByOriginCountryOrderByGooniesRatingDesc(Country country, Pageable pageable);

    Page<Movie> findAllByOriginCountryAndGenresContainsOrderByGooniesRatingDesc(Country country, Genre genre, Pageable pageable);

}
