package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.review.dto.ReviewDto;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReviewModelAssembler extends RepresentationModelAssemblerSupport<Review, ReviewDto> {

    public ReviewModelAssembler() {
        super(ReviewController.class, ReviewDto.class);
    }

    @Override
    public @NonNull ReviewDto toModel(@NonNull Review reviewEntity) {
        ReviewDto reviewModel = new ReviewDto(reviewEntity);
        reviewModel.add(linkTo(methodOn(ReviewController.class).getReviewById(reviewEntity.getId())).withSelfRel());
        return reviewModel;
    }

}
