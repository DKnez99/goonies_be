package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.common.persistence.EntityStatus;
import com.goonsquad.goonies.api.movie.dto.MovieDto;
import com.goonsquad.goonies.api.user.BaseUserDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.Instant;
import java.util.Date;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_REVIEW_MOVIE_RATING;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RATING_MAX;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_MOVIE_RATING_MIN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Relation(collectionRelation = "reviews")
public class ReviewDto extends RepresentationModel<ReviewDto> {

    private Long id;

    private BaseUserDto user;

    private MovieDto movie;

    @NotNull(message = REQUIRED_REVIEW_MOVIE_RATING)
    @Min(value = 1, message = REQUIRED_MOVIE_RATING_MIN)
    @Max(value = 100, message = REQUIRED_MOVIE_RATING_MAX)
    private Short rating;

    private String text;

    private boolean alreadyWatched;

    private Date watchDate;

    private WatchPlatform watchPlatform;

    private EntityStatus status;

    private Instant createdAd;

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.user = new BaseUserDto(review.getUser());
        this.movie = new MovieDto(review.getMovie());
        this.rating = review.getRating();
        this.text = review.getText();
        this.alreadyWatched = review.isAlreadyWatched();
        this.watchDate = review.getWatchDate();
        this.watchPlatform = review.getWatchPlatform();
        this.status = review.getStatus();
        this.createdAd = review.getCreatedAt();
    }
}
