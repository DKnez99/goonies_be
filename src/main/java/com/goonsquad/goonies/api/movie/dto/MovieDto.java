package com.goonsquad.goonies.api.movie.dto;

import com.goonsquad.goonies.api.movie.Movie;
import com.goonsquad.goonies.api.movie.MovieStatus;
import com.goonsquad.goonies.api.movie.genre.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_ORIGIN_COUNTRY;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RATING_MAX;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RATING_MIN;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_STATUS;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_TITLE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Relation(collectionRelation = "movies")
public class MovieDto extends RepresentationModel<MovieDto> {

    private Long id;

    @NotBlank(message = REQUIRED_MOVIE_TITLE)
    private String title;

    private String description;

    private String coverImageReference;

    @Builder.Default
    private List<String> genres = new ArrayList<>();

    private Date releaseDate;

    @NotBlank(message = REQUIRED_MOVIE_ORIGIN_COUNTRY)
    private String originCountry;

    private String imdbLink;

    @Min(value = 1, message = REQUIRED_MOVIE_RATING_MIN)
    @Max(value = 100, message = REQUIRED_MOVIE_RATING_MAX)
    private Short imdbRating;

    private String rottenTomatoesLink;

    @Min(value = 1, message = REQUIRED_MOVIE_RATING_MIN)
    @Max(value = 100, message = REQUIRED_MOVIE_RATING_MAX)
    private Short rottenTomatoesRating;

    private Short gooniesRating;

    @NotBlank(message = REQUIRED_MOVIE_STATUS)
    private MovieStatus status;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.coverImageReference = movie.getCoverImageReference();
        this.genres = movie.getGenres().stream().map(Genre::getName).collect(Collectors.toList());
        this.releaseDate = movie.getReleaseDate();
        this.originCountry = movie.getOriginCountry().getName();
        this.imdbLink = movie.getImdbLink();
        this.imdbRating = movie.getImdbRating();
        this.rottenTomatoesLink = movie.getRottenTomatoesLink();
        this.rottenTomatoesRating = movie.getRottenTomatoesRating();
        this.gooniesRating = movie.getGooniesRating();
        this.status = movie.getStatus();
    }

}
