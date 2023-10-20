package com.goonsquad.goonies.security.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goonsquad.goonies.exception.ExceptionDto;
import com.goonsquad.goonies.exception.ForbiddenException;
import com.goonsquad.goonies.security.user.UserDetailsServiceImpl;
import com.goonsquad.goonies.security.user.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.goonsquad.goonies.api.common.validation.ValidationConstants.TOKEN_NOT_VALID;
import static com.goonsquad.goonies.api.common.validation.ValidationConstants.USER_NOT_ACTIVE_OR_NONEXISTENT;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isNull(header) || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.split(" ")[1].trim();

        if (jwtUtil.isInvalid(token)) {
            setExceptionResponse(TOKEN_NOT_VALID, request, response);
            return;
        }

        UserPrincipal userPrincipal = userDetailsService.loadUserByUsername(jwtUtil.getSubject(token));

        if(isNull(userPrincipal) || !userPrincipal.isEnabled()) {
            setExceptionResponse(USER_NOT_ACTIVE_OR_NONEXISTENT, request, response);
            return;
        }

        userPrincipal.setAccessToken(token);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private void setExceptionResponse(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExceptionDto exception = new ExceptionDto(new ForbiddenException(message), new ServletWebRequest(request), HttpStatus.FORBIDDEN.value());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().println(objectMapper.writeValueAsString(exception));
    }

}
