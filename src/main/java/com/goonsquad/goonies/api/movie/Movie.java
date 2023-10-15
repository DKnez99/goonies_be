package com.goonsquad.goonies.api.movie;

import com.goonsquad.goonies.api.country.Country;
import com.goonsquad.goonies.api.movie.genre.Genre;
import com.goonsquad.goonies.api.review.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String coverImageReference;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private List<Review> reviews = new ArrayList<>();

    private Date releaseDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_country_id")
    private Country originCountry;

    private String imdbLink;

    private Short imdbRating;

    private String rottenTomatoesLink;

    private Short rottenTomatoesRating;

    @Formula("(SELECT AVG(r.rating) FROM Review r WHERE r.movie_id = id)")
    private Short gooniesRating;

    @Enumerated(EnumType.STRING)
    private MovieStatus status;

}
