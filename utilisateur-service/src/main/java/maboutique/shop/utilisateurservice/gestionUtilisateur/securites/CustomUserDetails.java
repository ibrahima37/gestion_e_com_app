package maboutique.shop.utilisateurservice.gestionUtilisateur.securites;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import maboutique.shop.commonsecurity.gestionSecurity.interfaces.IPersonnes;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Personne iPersonnes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        // Ajouter les profils
        iPersonnes.getProfils().forEach(profil ->
                authorities.add(new SimpleGrantedAuthority(profil.getCode()))
        );

        // Ajouter les permissions
        iPersonnes.getProfils().forEach(profil ->
                profil.getPermissions().forEach(permission ->
                        authorities.add(new SimpleGrantedAuthority(permission.getNom()))
                )
        );

        return authorities;
    }

    @Override
    public String getPassword() {

        return iPersonnes.getMotDePasse();
    }

    @Override
    public String getUsername() {

        return iPersonnes.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return Boolean.TRUE.equals(
                iPersonnes.getStatut()
        );
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return Boolean.TRUE.equals(
                iPersonnes.getStatut()
        );
    }
}