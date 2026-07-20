package maboutique.shop.boutiqueservice.gestionBoutique.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "cle-super-secrete-256-bit-minimum";

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
