package maboutique.shop.boutiqueservice.gestionBoutique.securites;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Personne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Personne personne;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        // Ajouter les profils
        personne.getProfils().forEach(profil ->
                authorities.add(new SimpleGrantedAuthority(profil.getCode()))
        );

        // Ajouter les permissions
        personne.getProfils().forEach(profil ->
                profil.getPermissions().forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission.getNom()))
                )
        );

        return authorities;
    }
//        return personne.getProfils()
//                .stream()
//                .flatMap(profil -> profil.getPermissions().stream())
//                .map(permission -> new SimpleGrantedAuthority(permission.getNom()))
//                .collect(Collectors.toSet());


    @Override
    public String getPassword() {

        return personne.getMotDePasse();
    }

    @Override
    public String getUsername() {

        return personne.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return Boolean.TRUE.equals(
                personne.getStatut()
        );
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return Boolean.TRUE.equals(
                personne.getStatut()
        );
    }
}