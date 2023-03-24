package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private String secret;
    public String generateToken(UserDetails user) {

        Map<String,Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){

        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String jwtToken){
        return getClaimFromToken(jwtToken,Claims::getSubject);
    }

    public boolean validateToken(String jwtToken, UserDetails userDetails) {
        String username = getUsernameFromToken(jwtToken);

        return username.equals(userDetails.getUsername()) && !isJwtTokenExpired(jwtToken);
    }

    private boolean isJwtTokenExpired(String jwtToken) {
        final Date expirationDate = getExpirationDateFromToken(jwtToken);
        return expirationDate.before(new Date());
    }

    private Date getExpirationDateFromToken(String jwtToken) {

        return getClaimFromToken(jwtToken,Claims::getExpiration);
    }
}
