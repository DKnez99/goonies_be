package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.country.Country;
import com.goonsquad.goonies.api.country.CountryService;
import com.goonsquad.goonies.api.movie.dto.CreateMovieDto;
import com.goonsquad.goonies.api.movie.genre.Genre;
import com.goonsquad.goonies.api.movie.genre.GenreService;
import com.goonsquad.goonies.exception.BadRequestException;
import com.goonsquad.goonies.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Set;
import java.util.stream.Collectors;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.MOVIE_ALREADY_EXISTS;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.MOVIE_RATING_WITHOUT_LINK;
import static java.util.Objects.isNull;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreService genreService;
    private final CountryService countryService;

    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(Movie.class, movieId));
    }

    public Page<Movie> findAll(final Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    public Page<Movie> findAllByGenreName(final String genreName, final Pageable pageable) {
        Genre genre = genreService.findByName(genreName);
        return movieRepository.findAllByGenresContains(genre, pageable);
    }

    public Page<Movie> findAllByOriginCountryName(final String countryName, final Pageable pageable) {
        Country country = countryService.findByName(countryName);
        return movieRepository.findAllByOriginCountry(country, pageable);
    }

    public Movie createMovie(CreateMovieDto createMovieDto) {
        if (movieRepository.existsByTitleAndReleaseDate(createMovieDto.getTitle(), createMovieDto.getReleaseDate())){
            throw new BadRequestException(MessageFormat.format(MOVIE_ALREADY_EXISTS, createMovieDto.getTitle(), createMovieDto.getReleaseDate()));
        }

//        if ((createMovieDto.getImdbLink().isBlank() && !isNull(createMovieDto.getImdbRating())) || (createMovieDto.getRottenTomatoesLink().isBlank() && !isNull(createMovieDto.getRottenTomatoesRating()))) {
//            throw new BadRequestException(MOVIE_RATING_WITHOUT_LINK);
//        }

        Country country = countryService.findByName(createMovieDto.getOriginCountry());
        Set<Genre> genres = createMovieDto.getGenres().stream()
                .map(genreService::findByName).collect(Collectors.toSet());

        Movie movie = Movie.builder()
                .title(createMovieDto.getTitle())
                .description(createMovieDto.getDescription())
                .genres(genres)
                .releaseDate(createMovieDto.getReleaseDate())
                .originCountry(country)
                .imdbLink(createMovieDto.getImdbLink())
                .imdbRating(createMovieDto.getImdbRating())
                .rottenTomatoesLink(createMovieDto.getRottenTomatoesLink())
                .rottenTomatoesRating(createMovieDto.getRottenTomatoesRating())
                .status(createMovieDto.getStatus())
                .build();

        return movieRepository.save(movie);
    }
}
