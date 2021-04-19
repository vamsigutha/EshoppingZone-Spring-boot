package com.eshoppingzone.productservice.auth.security;

import com.eshoppingzone.productservice.auth.bean.JwtAuthToken;
import com.eshoppingzone.productservice.auth.bean.JwtAuthenticatedProfile;
import com.eshoppingzone.productservice.auth.exception.InvalidTokenException;
import com.eshoppingzone.productservice.auth.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private final JwtService jwtService;

    public JwtAuthenticationProvider() {
        this(null);
    }

    @Autowired
    public JwtAuthenticationProvider(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @SneakyThrows
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Jws<Claims> claim = jwtService.verify((String) authentication.getCredentials());
            String currentUser = claim.getBody().getSubject();
            String role = claim.getBody().get("scope",String.class);

            JwtAuthenticatedProfile profile = new JwtAuthenticatedProfile(currentUser);
            profile.setUserRole(role);
            return profile;
        } catch (Exception e) {
            throw new JwtAuthenticationException("Invalid Token",e);
        }
    }

    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
