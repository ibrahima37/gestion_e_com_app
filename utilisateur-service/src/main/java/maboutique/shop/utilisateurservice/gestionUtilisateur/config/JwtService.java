//package maboutique.shop.utilisateurservice.gestionUtilisateur.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class JwtService {
//
//    @Value("${jwt.secret}")
//    private String SECRET;
//
//    private String expiration;
//
//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public String generateToken(UserDetails userDetails) {
//
//        List<String> roles = userDetails.getAuthorities()
//
//                .stream()
//
//                .map(GrantedAuthority::getAuthority)
//
//                .toList();
//
//        return Jwts.builder()
//
//                .subject(userDetails.getUsername())
//
//                .claim("roles", roles)
//
//                .issuedAt(new Date())
//
//                .expiration(
//                        new Date(
//                                System.currentTimeMillis() + expiration
//                        )
//                )
//
//                .signWith(getSigningKey())
//
//                .compact();
//    }
//
//    public Claims extractClaims(String token) {
//
//        return Jwts.parser()
//
//                .verifyWith(
//                        Keys.hmacShaKeyFor(
//                                SECRET.getBytes(StandardCharsets.UTF_8)
//                        )
//                )
//
//                .build()
//
//                .parseSignedClaims(token)
//
//                .getPayload();
//    }
//
//    public String extractUsername(String token) {
//
//        return extractClaims(token).getSubject();
//    }
//
//    public List<String> extractRoles(String token) {
//
//        return extractClaims(token)
//
//                .get("roles", List.class);
//    }
//}
