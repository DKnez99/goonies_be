package com.goonsquad.goonies.api.movie.dto;

import com.goonsquad.goonies.api.movie.MovieStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_GENRE;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_ORIGIN_COUNTRY;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RATING_MAX;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RATING_MIN;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RELEASE_DATE;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_STATUS;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_TITLE;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieDto {

    @NotBlank(message = REQUIRED_MOVIE_TITLE)
    private String title;

    private String description;

    private String coverImageReference;

    @Builder.Default
    @Size(min = 1, message = REQUIRED_MOVIE_GENRE)
    private List<String> genres = new ArrayList<>();

    @NotNull(message = REQUIRED_MOVIE_RELEASE_DATE)
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

    @NotNull(message = REQUIRED_MOVIE_STATUS)
    private MovieStatus status;

}
