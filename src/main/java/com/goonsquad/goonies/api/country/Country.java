package com.goonsquad.goonies.api.country;

import com.goonsquad.goonies.api.movie.Movie;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "originCountry", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> movies = new ArrayList<>();
}
