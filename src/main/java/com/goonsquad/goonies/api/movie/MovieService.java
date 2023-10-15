package com.goonsquad.goonies.api.movie;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.goonsquad.goonies.api.country.Country;
import com.goonsquad.goonies.api.country.CountryService;
import com.goonsquad.goonies.api.movie.genre.Genre;
import com.goonsquad.goonies.api.movie.genre.GenreService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final CountryService countryService;

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Page<Movie> findAll(Map<String, String> params, Pageable pageable) {
        final String COUNTRY_KEY = "country";
        final String GENRE_KEY = "genre";
        Country country = null;
        Genre genre = null;

        if (params.isEmpty()) {
            return movieRepository.findAll(pageable);
        }

        if (params.containsKey(COUNTRY_KEY)) {
            country = countryService.findByName(params.get(COUNTRY_KEY));
        }

        if (params.containsKey(GENRE_KEY)) {
            genre = genreService.findByName(params.get(GENRE_KEY));
        }

        if (genre != null && country != null) {
            return movieRepository.findAllByOriginCountryAndGenresContainsOrderByGooniesRatingDesc(country, genre, pageable);
        }

        if (genre != null) {
            return movieRepository.findAllByGenresContainsOrderByGooniesRatingDesc(genre, pageable);
        }

        if (country != null) {
            return movieRepository.findAllByOriginCountryOrderByGooniesRatingDesc(country, pageable);
        }

//        ArrayBuilders.BooleanBuilder builder = new ArrayBuilders.BooleanBuilder();
//
//        params.forEach( (String key, String value) -> {
//            StringPath column = Expressions.stringPath(qItem, key);
//            builder.and(column.eq(value));
//
//        });

        return null;
        // throw new BadRequest ();
    }

    public Page<Movie> findAllByGenreName(String genreName, Pageable pageable) {
        Genre genre = genreService.findByName(genreName);
        return movieRepository.findAllByGenresContainsOrderByGooniesRatingDesc(genre, pageable);
    }

    public Page<Movie> findAllByCountryName(String countryName, Pageable pageable) {
        Country country = countryService.findByName(countryName);
        return movieRepository.findAllByOriginCountryOrderByGooniesRatingDesc(country, pageable);
    }

}
