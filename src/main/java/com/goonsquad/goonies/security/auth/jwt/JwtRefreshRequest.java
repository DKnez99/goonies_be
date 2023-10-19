package com.goonsquad.goonies.security.auth.jwt;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.REQUIRED_REFRESH_TOKEN;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRefreshRequest {

    @NotBlank(message = REQUIRED_REFRESH_TOKEN)
    private String refreshToken;

}
