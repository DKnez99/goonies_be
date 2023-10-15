package com.goonsquad.goonies.api.movie;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Goonies API")
public class MovieController {

    private final MovieService movieService;

    private final MovieModelAssembler movieModelAssembler;

    private final PagedResourcesAssembler<Movie> pagedResourcesAssembler;

    @GetMapping("/movies")
    @Operation(description = "Returns all movies", summary = "Get all movies")
    public ResponseEntity<PagedModel<MovieDto>> getAllMovies(@RequestParam(required = false) Map<String, String> params, Pageable pageable) {
        Page<Movie> pagedEntities = movieService.findAll(params, pageable);
        PagedModel<MovieDto> pagedModel = pagedResourcesAssembler.toModel(pagedEntities, movieModelAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/movies/{id}")
    @Operation(description = "Returns movie by id", summary = "Get movies by id")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") Long id) {
        Movie movie = movieService.findById(id);
        MovieDto movieDto = movieModelAssembler.toModel(movie);
        return ResponseEntity.ok(movieDto);
    }


}
