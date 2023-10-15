package com.goonsquad.goonies.api.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Short> {

    Optional<Country> findByNameIgnoreCase(String name);

}
