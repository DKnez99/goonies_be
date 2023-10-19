package com.goonsquad.goonies.security.auth.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;
    private String refreshToken;

}