package com.goonsquad.goonies.api.country;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public Country findByName(String name) {
        return countryRepository.findByNameIgnoreCase(name).orElse(null);
    }

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}
