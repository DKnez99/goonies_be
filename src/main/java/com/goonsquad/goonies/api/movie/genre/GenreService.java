package com.goonsquad.goonies.api.movie.genre;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Page<Genre> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    public Genre findByName(String name) {
        return genreRepository.findByNameIgnoreCase(name).orElse(null);
    }
}
