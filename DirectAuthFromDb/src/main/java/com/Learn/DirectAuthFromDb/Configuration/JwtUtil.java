package com.Learn.DirectAuthFromDb.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private String SECRET_KEY = "your-very-long-secret-key-which-should-be-secure";
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}
//----------------------------------------------------------------------------------------------------------------------
	public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
//----------------------------------------------------------------------------------------------------------------------
    // Extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }
//----------------------------------------------------------------------------------------------------------------------
    
	public Claims extractAllClaims(String token) {
        return Jwts.parser()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }
//----------------------------------------------------------------------------------------------------------------------

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
//----------------------------------------------------------------------------------------------------------------------
	 // Generate the token using the username
	public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("roles", userDetails.getAuthorities().stream()
	                       .map(GrantedAuthority::getAuthority)
	                       .toList()); // List<String> like ["ROLE_USER", "ROLE_ADMIN"]
	    return createToken(claims, userDetails.getUsername());
	}

//----------------------------------------------------------------------------------------------------------------------
    // Create a JWT token with claims and subject
    private String createToken(Map<String, Object> claims, String subject) {
    	long expirationTime = 1000*60*60*10;
    	
    	return Jwts.builder()
    			   .setClaims(claims)
    			   .setSubject(subject)
    			   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                   .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                   .compact();
    }
//---------------------------------------------------------------------------------------------------------------------
 // Validate the token (ensure it matches the username and is not expired)
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
