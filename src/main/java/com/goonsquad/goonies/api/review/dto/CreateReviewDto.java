package com.goonsquad.goonies.api.review.dto;

import com.goonsquad.goonies.api.review.WatchPlatform;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Date;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateReviewDto {

    @NotNull(message = REQUIRED_REVIEW_MOVIE_RATING)
    @Min(value = 1, message = REQUIRED_MOVIE_RATING_MIN)
    @Max(value = 100, message = REQUIRED_MOVIE_RATING_MAX)
    private Short rating;

    private String text;

    private boolean alreadyWatched = false;

    private Date watchDate = Date.from(Instant.now());

    private WatchPlatform watchPlatform = WatchPlatform.TV;

}
