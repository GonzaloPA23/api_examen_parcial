package com.upc.api_examen_parcial_202120632.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//Clase 1
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 2 * 60 * 60 * 1000; // 2 hours

    @Value("${jwt.secret}")
    private String rgpaSecret;

    public String getUsernameFromToken(String rgpaToken) {
        return getClaimFromToken(rgpaToken, Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String rgpaToken) {
        return getClaimFromToken(rgpaToken, Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String rgpaToken, Function<Claims, T> rgpaClaimsResolver) {
        final Claims claims = getAllClaimsFromToken(rgpaToken);
        return rgpaClaimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String rgpaToken) {
        return Jwts.parser().setSigningKey(rgpaSecret).parseClaimsJws(rgpaToken).getBody();
    }
    private Boolean isTokenExpired(String rgpaToken) {
        final Date expiration = getExpirationDateFromToken(rgpaToken);
        return expiration.before(new Date());
    }
    public String generateToken(UserDetails rgpaUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", "Gonzalo");
        return doGenerateToken(claims, rgpaUserDetails.getUsername());
    }
    private String doGenerateToken(Map<String, Object> rgpaClaims, String rgpaSubject) {
        return Jwts.builder().setClaims(rgpaClaims).setSubject(rgpaSubject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, rgpaSecret).compact();
    }
    public Boolean validateToken(String rgpaToken, UserDetails rgpaUserDetails) {
        final String username = getUsernameFromToken(rgpaToken);
        return (username.equals(rgpaUserDetails.getUsername()) && !isTokenExpired(rgpaToken));
    }
}