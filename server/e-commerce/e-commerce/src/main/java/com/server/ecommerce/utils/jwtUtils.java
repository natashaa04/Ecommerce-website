package com.server.ecommerce.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.lang.ProcessHandle.Info;
import java.security.Key;
import java.util.Base64;

@Component
@Slf4j
public class jwtUtils {

    public static final String SECRET = "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";

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

    private Key getSignKey() {
    	try {
        log.info("Generating sign key...");
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        
        return Keys.hmacShaKeyFor(keyBytes);
    	}catch(Exception e){
    	
    		e.printStackTrace();
    		return null;
    	}
    }

  
            public String extractUsername(String token) {
        

                return extractClaim(token, Claims::getSubject);
            }

         
                public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
              
                    final Claims claims = extractAllClaims(token);
             

                    return claimsResolver.apply(claims);
                }
                
                
                private Claims extractAllClaims(String token) {
                    try {
                                         @SuppressWarnings("unchecked")
						Jwt<?, Claims> jwt = Jwts.parserBuilder()
                                                .setSigningKey(getSignKey())
                                                .build()
                                                .parse(token);
                        return jwt.getBody();
                    } catch (Exception e) {
                        log.info("JWT token parsing error: {}", e.getMessage());
                        e.printStackTrace();
                        return null; // Or handle the error accordingly
                    }
                }
//                
//  private Claims extractAllClaims(String token) {
//                	log.info("2");
//try {
//	JwtParserBuilder parserBuilder = Jwts.parserBuilder();
//	log.info("a");
//	parserBuilder.setSigningKey(getSignKey());
//	log.info("b");
//	JwtParser parser = parserBuilder.build();
//	log.info("c");
//	Jws<Claims> jws = parser.parseClaimsJws(token);
//	log.info("d");
//	Claims claims = jws.getBody();
//	log.info("e");
////	Claims claims = Jwts.parser().setSigningKey(getSignKey()).build().parseSignedClaims(token).getPayload();
//
//	return claims;
//                
//}  catch (MalformedJwtException e) {
//                        log.info("Malformed JWT token: {}");
//                        e.printStackTrace();
//                        return null; // Or handle the error accordingly
//                    }
//
//
//                }

//                private Claims extractAllClaims(String token) {
//                    return Jwts.parserBuilder().setSigningKey( getSignKey()).build().parseClaimsJws(token).getBody();
//                }
            

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


