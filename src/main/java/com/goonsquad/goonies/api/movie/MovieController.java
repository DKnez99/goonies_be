package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.movie.dto.CreateMovieDto;
import com.goonsquad.goonies.api.movie.dto.MovieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Goonies API")
public class MovieController {

    private final MovieService movieService;

    private final MovieModelAssembler movieModelAssembler;

    private final PagedResourcesAssembler<Movie> pagedMovieModelsAssembler;

    @GetMapping("/movies")
    @Operation(description = "Returns all movies", summary = "Get all movies")
    public ResponseEntity<PagedModel<MovieDto>> getAllMovies(@PageableDefault(sort = {"releaseDate", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Movie> moviePage = movieService.findAll(pageable);
        return ResponseEntity.ok(pagedMovieModelsAssembler.toModel(moviePage, movieModelAssembler));
    }

    @GetMapping("/movies-by-genre/{genreName}")
    @Operation(description = "Returns all movies by genre name", summary = "Get all movies by genre name")
    public ResponseEntity<PagedModel<MovieDto>> getAllMoviesByGenreName(@PathVariable(name = "genreName") final String genreName, @PageableDefault(sort = {"releaseDate", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Movie> moviePage = movieService.findAllByGenreName(genreName, pageable);
        return ResponseEntity.ok(pagedMovieModelsAssembler.toModel(moviePage, movieModelAssembler));
    }

    @GetMapping("/movies-by-country/{countryName}")
    @Operation(description = "Returns all movies by origin country name", summary = "Get all movies by origin country name")
    public ResponseEntity<PagedModel<MovieDto>> getAllMoviesByOriginCountryName(@PathVariable(name = "countryName") final String countryName, @PageableDefault(sort = {"releaseDate", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Movie> moviePage = movieService.findAllByOriginCountryName(countryName, pageable);
        return ResponseEntity.ok(pagedMovieModelsAssembler.toModel(moviePage, movieModelAssembler));
    }

    @GetMapping("/movies/{id}")
    @Operation(description = "Returns movie by movie ID", summary = "Get movie by movie ID")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(name = "id") final Long id) {
        Movie movie = movieService.findById(id);
        return ResponseEntity.ok(movieModelAssembler.toModel(movie));
    }

    @PostMapping("/movies")
    @Operation(description = "Create new movie", summary = "Create new movie")
    public ResponseEntity<MovieDto> createMovie(@RequestBody @Valid CreateMovieDto createMovieDto) {
        Movie movie = movieService.createMovie(createMovieDto);
        return ResponseEntity.ok(movieModelAssembler.toModel(movie));
    }

}
