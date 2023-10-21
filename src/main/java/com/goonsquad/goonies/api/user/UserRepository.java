package com.goonsquad.goonies.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u " +
            "WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
    Optional<User> findByUsernameOrEmailIgnoreCase(String usernameOrEmail);

}