package com.goonsquad.goonies.security.auth;

import com.goonsquad.goonies.security.auth.jwt.JwtRefreshRequest;
import com.goonsquad.goonies.security.auth.jwt.JwtRequest;
import com.goonsquad.goonies.security.auth.jwt.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@SecurityRequirements
@RequiredArgsConstructor
@Tag(name = "Authentication API")
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/token")
    @Operation(description = "Returns access and refresh token based on user credentials", summary = "Get JWTs based on user credentials")
    public ResponseEntity<JwtResponse> getJwtWithCredentials(@RequestBody @Valid JwtRequest request) {
        return ResponseEntity.ok(authService.getAccessTokenWithCredentials(request));
    }

    @PostMapping("/refresh-token")
    @Operation(description = "Returns access and refresh token based on previous refresh token", summary = "Get JWTs based on previous refresh token")
    public ResponseEntity<JwtResponse> getJwtWithRefreshToken(@RequestBody @Valid JwtRefreshRequest request) {
        return ResponseEntity.ok(authService.getAccessTokenWithRefreshToken(request));
    }

}
