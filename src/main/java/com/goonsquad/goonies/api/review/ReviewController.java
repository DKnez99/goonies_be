package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.review.dto.CreateReviewDto;
import com.goonsquad.goonies.api.review.dto.ReviewDto;
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
@Tag(name = "Reviews API")
@RolesAllowed({"USER", "ADMIN"})
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewModelAssembler reviewModelAssembler;
    private final PagedResourcesAssembler<Review> pagedReviewModelAssembler;

    @GetMapping("/v1/movies/{movieId}/reviews")
    @Operation(description = "Returns all reviews for a movie by movie ID", summary = "Get all reviews for a movie")
    public ResponseEntity<PagedModel<ReviewDto>> getAllReviewsByMovie(@PathVariable final Long movieId, @PageableDefault(sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Review> reviewPage = reviewService.findAllByMovieId(movieId, pageable);
        return ResponseEntity.ok(pagedReviewModelAssembler.toModel(reviewPage, reviewModelAssembler));
    }

    @GetMapping("/v1/users/{username}/reviews")
    @Operation(description = "Returns all reviews made by a specified user", summary = "Get all reviews by user")
    public ResponseEntity<PagedModel<ReviewDto>> getAllReviewsByUser(@PathVariable final String username, @PageableDefault(sort = {"createdAt", "id"}, direction = Sort.Direction.DESC) final Pageable pageable) {
        Page<Review> reviewPage = reviewService.findAllByUserUsername(username, pageable);
        return ResponseEntity.ok(pagedReviewModelAssembler.toModel(reviewPage, reviewModelAssembler));
    }

    @GetMapping("/v1/reviews/{id}")
    @Operation(description = "Returns a single review by ID", summary = "Get single review")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable final Long id) {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(reviewModelAssembler.toModel(review));
    }

    @PostMapping("/v1/movies/{movieId}/reviews")
    @Operation(description = "Creates a new review", summary = "Create review")
    public ResponseEntity<ReviewDto> createReview(@PathVariable final Long movieId, @RequestBody @Valid CreateReviewDto createReviewDto) {
        Review createdReview = reviewService.create(movieId, createReviewDto);
        return ResponseEntity.ok(reviewModelAssembler.toModel(createdReview));
    }

    @DeleteMapping("/v1/reviews/{id}")
    @Operation(description = "Deactivates a review by ID", summary = "Delete review")
    public ResponseEntity<ReviewDto> deleteReview(@PathVariable final Long id) {
        Review deletedReview = reviewService.delete(id);
        return ResponseEntity.ok(reviewModelAssembler.toModel(deletedReview));
    }

}