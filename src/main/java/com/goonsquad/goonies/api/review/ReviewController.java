package com.goonsquad.goonies.api.review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reviews API")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewModelAssembler reviewModelAssembler;
    private final PagedResourcesAssembler<Review> pagedReviewModelAssembler;

    @GetMapping("/movies/{movieId}/reviews")
    @Operation(description = "Returns all reviews for a movie", summary = "Get all reviews for a movie")
    public ResponseEntity<PagedModel<ReviewDto>> getAllReviewsByMovieId(@PathVariable(name = "movieId") final Long movieId, @PageableDefault(sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Review> reviewPage = reviewService.findAllByMovieId(movieId, pageable);
        return ResponseEntity.ok(pagedReviewModelAssembler.toModel(reviewPage, reviewModelAssembler));
    }

    @GetMapping("/reviews/{id}")
    @Operation(description = "Returns review by id", summary = "Get review by id")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(name = "id") final Long id) {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(reviewModelAssembler.toModel(review));
    }
}
