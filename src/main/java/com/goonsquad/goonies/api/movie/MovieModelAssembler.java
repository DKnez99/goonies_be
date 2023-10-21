package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.movie.dto.MovieDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MovieModelAssembler extends RepresentationModelAssemblerSupport<Movie, MovieDto> {

    public MovieModelAssembler() {
        super(MovieController.class, MovieDto.class);
    }

    @Override
    public @NonNull MovieDto toModel(@NonNull Movie movieEntity) {
        MovieDto movieModel = new MovieDto(movieEntity);
        movieModel.add(linkTo(methodOn(MovieController.class).getMovieById(movieEntity.getId())).withSelfRel());
        return movieModel;
    }

}
