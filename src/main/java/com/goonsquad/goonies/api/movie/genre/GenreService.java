package com.goonsquad.goonies.api.movie.genre;

import com.goonsquad.goonies.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public Page<Genre> findAll(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    public Genre findByName(final String name) {
        return genreRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException(Genre.class, name));
    }

}
