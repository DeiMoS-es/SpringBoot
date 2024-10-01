package com.movie.rating.user.services.impl;
import com.movie.rating.user.services.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${secret.key}")
    private String SECRET_KEY;

    public String getToken(UserDetails request) {
        return getToken(new HashMap<>(), request);
    }

    private String getToken(Map<String, Objects> extraClaims, UserDetails request) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(request.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
