package com.server.ecommerce.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import java.security.Key;
import java.util.Base64;

@Component

public class jwtUtils {

    public static final String SECRET = "5367566859783373367639792F423F45A71347437536756685978337536763979789789";

 public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }
  

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith((java.security.Key)getSignKey(),SignatureAlgorithm.HS256)
                .compact();
        
    }
//    private java.security.Key getSignKey() {
//        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
//        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
//    }

    
        private java.security.Key getSignKey() {
               byte[] keyBytes = Base64.getDecoder().decode(SECRET);
               return  Keys.hmacShaKeyFor(keyBytes);
}
  
            public String extractUsername(String token) {
                return extractClaim(token, Claims::getSubject);
            }

         
                public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
                    final Claims claims = extractAllClaims(token);
                    return claimsResolver.apply(claims);
                }

                private Claims extractAllClaims(String token) {
                    return Jwts.parserBuilder().setSigningKeyResolver((SigningKeyResolver) getSignKey()).build().parseClaimsJws(token).getBody();
                }

                public Date extractExpiration(String token) {
                    return extractClaim(token, Claims::getExpiration);
                }

                private Boolean isTokenExpired(String token) {
                    return extractExpiration(token).before(new Date());
                }

                public Boolean validateToken(String token, UserDetails userDetails) {
                    final String username = extractUsername(token);
                    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
                }
            }


