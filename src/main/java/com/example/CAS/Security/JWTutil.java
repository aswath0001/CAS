package com.example.CAS.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.function.Function;

public class JWTutil {
    private final String SecretKey = "aswath@123";
    private final long ExpirationTime=60*60*1000;

    public String GenerateToken(String userName){
        return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ExpirationTime))
                .signWith(SignatureAlgorithm.HS256,SecretKey)
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public  Boolean validateToken(String token,String userName){
        String extractedUserName =extractUsername(token);
        return (extractedUserName.equals(userName) && !isTokenExpired(token));
    }
   private  Boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());

   }
   private  Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
   }
   public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final  Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
   }
   private  Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .parseClaimsJws(token)
                .getBody();
   }
}
