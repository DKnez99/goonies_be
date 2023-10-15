package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.common.persistence.EntityStatus;
import com.goonsquad.goonies.api.movie.genre.Genre;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDto extends RepresentationModel<MovieDto> {

    public Long id;

    @NotBlank(message = "placeholder")
    public String title;

    public String description;

    public String coverImageReference;

    @Builder.Default
    private List<String> genres = new ArrayList<>();

    public Date releaseDate;

    @NotBlank(message = "placeholder")
    private String originCountry;

    public String imdbLink;

    public Short imdbRating;

    public String rottenTomatoesLink;

    public Short rottenTomatoesRating;

    public Short gooniesRating;

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
