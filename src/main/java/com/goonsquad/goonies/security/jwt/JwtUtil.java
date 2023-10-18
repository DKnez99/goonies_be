package com.goonsquad.goonies.security.jwt;

import com.goonsquad.goonies.api.user.User;
import com.goonsquad.goonies.exception.JwtException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.JWT_PARSING_EXCEPTION;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.JWT_SIGNATURE_EXCEPTION;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.JWT_VERIFICATION_EXCEPTION;

@Slf4j
@Component
public class JwtUtil {

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.access-token.expiration-time-in-minutes}")
    private int accessTokenExpirationTimeInMinutes;
    @Value("${jwt.refresh-token.expiration-time-in-minutes}")
    private int refreshTokenExpirationTimeInMinutes;
    @Value("${jwt.lasting-refresh-token.expiration-time-in-days}")
    private int lastingRefreshTokenExpirationTimeInDays;

    private String secret;
    private JWSSigner signer;
    private JWSHeader header;

    private static final String REMEMBER_ME_CLAIM_NAME = "rememberMe";

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        try {
            this.secret = secret;
            this.signer = new MACSigner(secret);
            this.header = new JWSHeader(JWSAlgorithm.HS512);
        }
        catch (KeyLengthException e) {
            log.error("Error creating a JWT signing algorithm: {}", e.getMessage(), e);
        }
    }

    public String generateAccessToken(User user, boolean rememberMe) {
        JWTClaimsSet jwtClaimsSet = generateAccessTokenClaims(user).claim(REMEMBER_ME_CLAIM_NAME, rememberMe).build();
        return signJWT(jwtClaimsSet);
    }

    public String generateRefreshToken(User user, boolean rememberMe) {
        JWTClaimsSet.Builder jwtClaimsSetBuilder = rememberMe ? generateLastingRefreshTokenClaims(user).claim(REMEMBER_ME_CLAIM_NAME, true) : generateRefreshTokenClaims(user).claim(REMEMBER_ME_CLAIM_NAME, false);
        return signJWT(jwtClaimsSetBuilder.build());
    }

    public boolean isValid(String token) {
        try{
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secret);
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            return !signedJWT.verify(verifier) || expirationTime.before(new Date());
        } catch (ParseException e) {
            log.error("Exception occurred while parsing JWT token: {}", e.getMessage(), e);
            throw new JwtException(JWT_PARSING_EXCEPTION);
        } catch (JOSEException e) {
            log.error("Exception occurred while verifying JWT token: {}", e.getMessage(), e);
            throw new JwtException(JWT_VERIFICATION_EXCEPTION);
        }
    }

    /**
    Returns username associated with the token.
     */
    public String getSubject(String token) {
        try{
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            log.error("Exception occurred while parsing JWT token: {}", e.getMessage(), e);
            throw new JwtException(JWT_PARSING_EXCEPTION);
        }
    }

    public boolean isRememberMe(String token) {
        try{
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getClaim(REMEMBER_ME_CLAIM_NAME) == Boolean.TRUE;
        } catch (ParseException e) {
            log.error("Exception occurred while parsing JWT token: {}", e.getMessage(), e);
            throw new JwtException(JWT_PARSING_EXCEPTION);
        }
    }

    private String signJWT(JWTClaimsSet jwtClaimsSet) {
        try{
            SignedJWT signedJWT = new SignedJWT(header, jwtClaimsSet);
            signedJWT.sign(signer);
            return signedJWT.serialize();
        } catch (JOSEException e) {
            log.error("Exception occurred while signing JWT token: {}", e.getMessage(), e);
            throw new JwtException(JWT_SIGNATURE_EXCEPTION);
        }
    }

    private JWTClaimsSet.Builder generateCommonTokenClaims(User user) {
        return new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("userId", user.getId())
                .claim("roles", user.getRoles())
                .issuer(issuer);
    }

    private Date getAccessTokenExpirationTime() {
        return Date.from(Instant.now().plus(accessTokenExpirationTimeInMinutes, ChronoUnit.MINUTES));
    }

    private Date getRefreshTokenExpirationTime() {
        return Date.from(Instant.now().plus(refreshTokenExpirationTimeInMinutes, ChronoUnit.MINUTES));
    }

    private Date getLastingRefreshTokenExpirationTime() {
        return Date.from(Instant.now().plus(lastingRefreshTokenExpirationTimeInDays, ChronoUnit.DAYS));
    }

    private JWTClaimsSet.Builder generateAccessTokenClaims(User user) {
        return generateCommonTokenClaims(user).expirationTime(getAccessTokenExpirationTime());
    }

    private JWTClaimsSet.Builder generateRefreshTokenClaims(User user) {
        return generateCommonTokenClaims(user).expirationTime(getRefreshTokenExpirationTime());
    }

    private JWTClaimsSet.Builder generateLastingRefreshTokenClaims(User user) {
        return generateCommonTokenClaims(user).expirationTime(getLastingRefreshTokenExpirationTime());
    }

}
