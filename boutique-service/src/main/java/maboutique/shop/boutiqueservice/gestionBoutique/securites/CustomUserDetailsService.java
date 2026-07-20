package maboutique.shop.boutiqueservice.gestionBoutique.securites;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Personne;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.PersonneRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonneRepository personneRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Personne personne = personneRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        return new CustomUserDetails(personne);
    }
}
