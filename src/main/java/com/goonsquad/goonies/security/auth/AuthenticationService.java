package com.goonsquad.goonies.security.auth;

import com.goonsquad.goonies.api.user.User;
import com.goonsquad.goonies.api.user.UserService;
import com.goonsquad.goonies.exception.ForbiddenException;
import com.goonsquad.goonies.exception.JwtException;
import com.goonsquad.goonies.security.auth.jwt.JwtRefreshRequest;
import com.goonsquad.goonies.security.auth.jwt.JwtRequest;
import com.goonsquad.goonies.security.auth.jwt.JwtResponse;
import com.goonsquad.goonies.security.auth.jwt.JwtUtil;
import com.goonsquad.goonies.security.user.UserPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.AUTHENTICATION_FAILED;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.TOKEN_NOT_VALID;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authManager;

    public JwtResponse getAccessTokenWithCredentials(JwtRequest request) {
        try {
            Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));
            User user = ((UserPrincipal) authenticate.getPrincipal()).getUser();
            return createJwtResponse(user, request.isRememberMe());
        } catch (AuthenticationException e) {
            throw new ForbiddenException(AUTHENTICATION_FAILED);
        }
    }

    public JwtResponse getAccessTokenWithRefreshToken(JwtRefreshRequest request) {
        if (jwtUtil.isInvalid(request.getRefreshToken())) {
            throw new JwtException(TOKEN_NOT_VALID);
        }

        String usernameOrEmail = jwtUtil.getSubject(request.getRefreshToken());
        User user = userService.findByUsernameOrEmail(usernameOrEmail);
        return createJwtResponse(user, jwtUtil.isRememberMe(request.getRefreshToken()));
    }

    private JwtResponse createJwtResponse(User user, boolean rememberMe) {
        String accessToken = jwtUtil.generateAccessToken(user, rememberMe);
        String refreshToken = jwtUtil.generateRefreshToken(user, rememberMe);
        return new JwtResponse(accessToken, refreshToken);
    }

}
