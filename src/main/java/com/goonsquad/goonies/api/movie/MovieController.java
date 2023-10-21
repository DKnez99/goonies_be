package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.movie.dto.CreateMovieDto;
import com.goonsquad.goonies.api.movie.dto.MovieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Movies API")
public class MovieController {

    private final MovieService movieService;
    private final MovieModelAssembler movieModelAssembler;
    private final PagedResourcesAssembler<Movie> pagedMovieModelsAssembler;

    @GetMapping("/v1/movies")
    @Operation(description = "Returns all movies", summary = "Get all movies")
    public ResponseEntity<PagedModel<MovieDto>> getAllMovies(@PageableDefault(sort = {"releaseDate", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Movie> moviePage = movieService.findAll(pageable);
        return ResponseEntity.ok(pagedMovieModelsAssembler.toModel(moviePage, movieModelAssembler));
    }

    @GetMapping("/v1/genre/{genreName}/movies")
    @Operation(description = "Returns all movies by genre name", summary = "Get all movies by genre name")
    public ResponseEntity<PagedModel<MovieDto>> getAllMoviesByGenreName(@PathVariable final String genreName, @PageableDefault(sort = {"releaseDate", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Movie> moviePage = movieService.findAllByGenreName(genreName, pageable);
        return ResponseEntity.ok(pagedMovieModelsAssembler.toModel(moviePage, movieModelAssembler));
    }

    @GetMapping("/v1/country/{countryName}/movies")
    @Operation(description = "Returns all movies by origin country name", summary = "Get all movies by origin country name")
    public ResponseEntity<PagedModel<MovieDto>> getAllMoviesByOriginCountryName(@PathVariable final String countryName, @PageableDefault(sort = {"releaseDate", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Movie> moviePage = movieService.findAllByOriginCountryName(countryName, pageable);
        return ResponseEntity.ok(pagedMovieModelsAssembler.toModel(moviePage, movieModelAssembler));
    }

    @GetMapping("/v1/movies/{id}")
    @Operation(description = "Returns single movie by movie ID", summary = "Get movie by ID")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable final Long id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movieModelAssembler.toModel(movie));
    }

    @PostMapping("/v1/movies")
    @RolesAllowed({"ADMIN"})
    @Operation(description = "Creates a new movie", summary = "Create new movie")
    public ResponseEntity<MovieDto> createMovie(@RequestBody @Valid final CreateMovieDto createMovieDto) {
        Movie createdMovie = movieService.create(createMovieDto);
        return ResponseEntity.ok(movieModelAssembler.toModel(createdMovie));
    }

    @DeleteMapping("/v1/movies/{id}")
    @RolesAllowed({"ADMIN"})
    @Operation(description = "Deactivates a movie", summary = "Delete movie")
    public ResponseEntity<MovieDto> deleteMovie(@PathVariable(name = "id") Long id) {
        Movie deletedMovie = movieService.delete(id);
        return ResponseEntity.ok(movieModelAssembler.toModel(deletedMovie));
    }

}
