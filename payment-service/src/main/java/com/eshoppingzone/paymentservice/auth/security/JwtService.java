package com.eshoppingzone.paymentservice.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class JwtService {


    public JwtService() {

    }



    public Jws<Claims> verify(String token) throws IOException, URISyntaxException {

        return Jwts.parser().setSigningKey("customsecretkey").parseClaimsJws(token);
    }
}
