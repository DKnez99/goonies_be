package com.goonsquad.goonies.api.user;

import com.goonsquad.goonies.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsernameOrEmail(final String usernameOrEmail) {
        return userRepository.findByUsernameOrEmailIgnoreCase(usernameOrEmail)
                .orElseThrow(() -> new EntityNotFoundException(User.class, usernameOrEmail));
    }

}