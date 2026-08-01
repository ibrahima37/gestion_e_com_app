package maboutique.shop.utilisateurservice.gestionUtilisateur.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails){

        return Jwts.builder()

                .subject(
                        userDetails.getUsername()
                )

                .issuedAt(
                        new Date()
                )

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000
                        )
                )

                .signWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()
                        )
                )

                .compact();
    }

//    public String extractUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)))
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }

    public String extractUsername(
            String token){

        return Jwts.parser()
                .verifyWith(
                        Keys.hmacShaKeyFor(
                                SECRET.getBytes()
                        )
                )
                .build()

                .parseSignedClaims(token)

                .getPayload()
                .getSubject();
    }
}
