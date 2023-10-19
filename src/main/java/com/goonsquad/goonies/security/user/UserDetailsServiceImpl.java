package com.goonsquad.goonies.security.user;

import com.goonsquad.goonies.api.user.User;
import com.goonsquad.goonies.api.user.UserService;
import com.goonsquad.goonies.api.user.UserStatus;
import com.goonsquad.goonies.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByUsernameOrEmail(username);
            return new UserPrincipal(user);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

}
