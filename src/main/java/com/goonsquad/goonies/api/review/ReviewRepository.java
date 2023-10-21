package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.movie.Movie;
import com.goonsquad.goonies.api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByMovie(Movie movie, Pageable pageable);

    Page<Review> findAllByUser(User user, Pageable pageable);

}
