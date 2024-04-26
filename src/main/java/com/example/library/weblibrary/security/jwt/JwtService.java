package com.example.library.weblibrary.config;

import io.jsonwebtoken.Claims;
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
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpirationInMs;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpirationInMs;

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token
     * @return The username extracted from the token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the JWT token using the provided function.
     *
     * @param token          The JWT token
     * @param claimsResolver A function to resolve the claim from the token
     * @param <T>            The type of the claim
     * @return The resolved claim
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the JWT token.
     *
     * @param token The JWT token
     * @return All claims extracted from the token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves the signing key used for JWT verification.
     *
     * @return The signing key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Generates a JWT token for the given user details and custom claims.
     *
     * @param claims          Custom claims to be included in the token
     * @param userDetails     The user details to be included in the token
     * @param expirationInMs  Expiration time for the token in milliseconds
     * @return The generated JWT token
     */
    public String buildToken(Map<String, Object> claims, UserDetails userDetails, long expirationInMs) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationInMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generates a JWT token for the given user details.
     *
     * @param userDetails The user details to be included in the token
     * @return The generated JWT token
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generates a JWT token for the given user details and custom claims.
     *
     * @param claims      Custom claims to be included in the token
     * @param userDetails The user details to be included in the token
     * @return The generated JWT token
     */
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims, userDetails, jwtExpirationInMs);
    }

    /**
     * Generates a refresh JWT token for the given user details.
     *
     * @param userDetails The user details to be included in the token
     * @return The generated refresh JWT token
     */
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, refreshTokenExpirationInMs);
    }

    /**
     * Validates whether a given JWT token is valid.
     *
     * @param token       The JWT token to validate
     * @param userDetails The user details to compare with the token
     * @return True if the token is valid, otherwise false
     */
    public boolean isValidToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    /**
     * Checks whether a given JWT token has expired.
     *
     * @param token The JWT token to check
     * @return True if the token is expired, otherwise false
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date from the JWT token.
     *
     * @param token The JWT token
     * @return The expiration date extracted from the token
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
