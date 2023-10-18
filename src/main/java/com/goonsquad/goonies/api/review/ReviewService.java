package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.movie.Movie;
import com.goonsquad.goonies.api.movie.MovieService;
import com.goonsquad.goonies.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final MovieService movieService;

    public Page<Review> findAllByMovieId(final Long movieId, final Pageable pageable) {
        Movie movie = movieService.findById(movieId);
        return reviewRepository.findAllByMovie(movie, pageable);
    }

    public Review findById(final Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(Review.class, reviewId));
    }

}
