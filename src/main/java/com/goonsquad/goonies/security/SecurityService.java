package com.goonsquad.goonies.security;

import com.goonsquad.goonies.api.user.User;
import com.goonsquad.goonies.security.auth.jwt.JwtUtil;
import com.goonsquad.goonies.security.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final JwtUtil jwtUtil;

    public User getAuthenticatedUser(){
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    public boolean isAccessTokenActive(Authentication authentication) {
        String accessToken = ((UserPrincipal) authentication.getPrincipal()).getAccessToken();
        return !jwtUtil.isInvalid(accessToken);
    }

}
