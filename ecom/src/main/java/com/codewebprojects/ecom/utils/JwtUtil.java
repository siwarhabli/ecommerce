package com.codewebprojects.ecom.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.security.Key;

@Component
public class JwtUtil {

    public static final String SECRET = "536756685970337336739792F423F4528482B4D62516554685721245642458425745785485987589578957895789578957895789578957895789578957895789"; // Complete the truncated string if known, or assume it's long enough


    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        // Decode the secret string to a byte array to create a secure key
        long expirationTimeMillis = System.currentTimeMillis() + 10000L * 60 * 30; // 5000 hours

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(expirationTimeMillis)) // Using the calculated expiration
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    private Key getSignKey() {
        // Decodes the base64 encoded SECRET string into a byte array
        byte[] keybytes = Decoders.BASE64.decode(SECRET);
        // Creates an HMAC-SHA key from the decoded bytes
        return Keys.hmacShaKeyFor(keybytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}