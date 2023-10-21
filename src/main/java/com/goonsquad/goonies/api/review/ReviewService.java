package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.common.persistence.EntityStatus;
import com.goonsquad.goonies.api.movie.Movie;
import com.goonsquad.goonies.api.movie.MovieService;
import com.goonsquad.goonies.api.review.dto.CreateReviewDto;
import com.goonsquad.goonies.api.user.User;
import com.goonsquad.goonies.api.user.UserService;
import com.goonsquad.goonies.exception.EntityNotFoundException;
import com.goonsquad.goonies.security.SecurityService;
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
    private final UserService userService;
    private final SecurityService securityService;

    public Page<Review> findAllByMovieId(final Long movieId, final Pageable pageable) {
        Movie movie = movieService.findById(movieId);
        return reviewRepository.findAllByMovie(movie, pageable);
    }

    public Page<Review> findAllByUserUsername(final String username, final Pageable pageable) {
        User user = userService.findByUsernameOrEmail(username);
        return reviewRepository.findAllByUser(user, pageable);
    }

    public Review findById(final Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Review.class, id));
    }

    public Review create(final Long movieId, final CreateReviewDto createReviewDto) {
        Movie movie = movieService.findById(movieId);
        Review review = Review.builder()
                .user(securityService.getAuthenticatedUser())
                .movie(movie)
                .rating(createReviewDto.getRating())
                .text(createReviewDto.getText())
                .alreadyWatched(createReviewDto.isAlreadyWatched())
                .watchDate(createReviewDto.getWatchDate())
                .watchPlatform(createReviewDto.getWatchPlatform())
                .status(EntityStatus.ACTIVE)
                .build();

        return reviewRepository.save(review);
    }

    public Review delete(final Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Review.class, id));
        return reviewRepository.save(review);
    }

}
