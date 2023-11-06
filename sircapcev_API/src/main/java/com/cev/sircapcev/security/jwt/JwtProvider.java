package com.cev.sircapcev.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.cev.sircapcev.security.securityService.UserPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authenticate) {
        UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();
        return Jwts.builder()
                .signWith(getKey(secret))
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("------------", getRoles(userPrincipal))
                .claim("------------", "----------------------")
                .compact();

    }

    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey(secret))
                .build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret))
                    .build().parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Expired token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token");
        } catch (MalformedJwtException e) {
            logger.error("Malformed token");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Fail token");
        }
        return false;
    }

    private List<String> getRoles(UserPrincipal principal) {
        return principal.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private Key getKey(String secret) {
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
