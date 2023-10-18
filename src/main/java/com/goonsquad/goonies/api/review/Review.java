package com.goonsquad.goonies.api.review;

import com.goonsquad.goonies.api.common.persistence.AuditableEntity;
import com.goonsquad.goonies.api.common.persistence.EntityStatus;
import com.goonsquad.goonies.api.movie.Movie;
import com.goonsquad.goonies.api.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Review extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false, updatable = false)
    private Movie movie;

    private Short rating;

    private String text;

    private boolean alreadyWatched;

    private Date watchDate;

    @Enumerated(EnumType.STRING)
    private WatchPlatform watchPlatform;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;
}
