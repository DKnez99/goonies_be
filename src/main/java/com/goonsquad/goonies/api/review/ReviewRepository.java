package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByMovie(Movie movie, Pageable pageable);

}
