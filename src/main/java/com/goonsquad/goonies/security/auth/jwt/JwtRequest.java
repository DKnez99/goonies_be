package com.goonsquad.goonies.security.auth.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_PASSWORD;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_USERNAME_OR_EMAIL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    @NotBlank(message = REQUIRED_USERNAME_OR_EMAIL)
    private String usernameOrEmail;

    @NotBlank(message = REQUIRED_PASSWORD)
    private String password;

    private boolean rememberMe = false;

}
