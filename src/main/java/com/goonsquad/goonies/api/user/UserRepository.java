package com.goonsquad.goonies.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u " +
            "WHERE u.username = LOWER(:usernameOrEmail) OR u.email = LOWER(:usernameOrEmail)")
    Optional<User> findByUsernameOrEmailIgnoreCase(String usernameOrEmail);

    @Query(value = "SELECT u FROM User u " +
            "WHERE (u.username = LOWER(:usernameOrEmail) OR u.email = LOWER(:usernameOrEmail)) AND u.status = 'ACTIVE'")
    Optional<User> findActiveByUsernameOrEmailIgnoreCase(String usernameOrEmail);

}
