package com.goonsquad.goonies.api.movie;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MovieModelAssembler extends RepresentationModelAssemblerSupport<Movie, MovieDto> {

    public MovieModelAssembler() {
        super(MovieController.class, MovieDto.class);
    }

    @Override
    public MovieDto toModel(Movie entity) {
        MovieDto model = new MovieDto(entity);
        model.add(linkTo(methodOn(MovieController.class).getMovieById(entity.getId())).withSelfRel());
        return model;
    }

    @Override
    public CollectionModel<MovieDto> toCollectionModel(Iterable<? extends Movie> entities) {
        CollectionModel<MovieDto> models = super.toCollectionModel(entities);
        models.add(linkTo(methodOn(MovieController.class).getAllMovies(null, null)).withSelfRel());
        return models;
    }
}
