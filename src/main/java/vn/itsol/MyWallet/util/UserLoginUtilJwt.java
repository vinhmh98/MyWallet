package vn.itsol.MyWallet.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class UserLoginUtilJwt {
    private String secret ="victoria_secret";

    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    private <T> T extractClaims(String token, Function<Claims,T>claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
    }

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    public String createToken(Map<String, Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    public boolean isValidateToken(String token,UserDetails userDetails ){
        final String username =extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
