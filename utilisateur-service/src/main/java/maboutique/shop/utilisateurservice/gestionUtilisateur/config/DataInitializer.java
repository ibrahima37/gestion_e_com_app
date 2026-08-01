package maboutique.shop.utilisateurservice.gestionUtilisateur.config;

import lombok.RequiredArgsConstructor;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Permission;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Profil;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PermissionRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PersonneRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.ProfilRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProfilRepository profilRepository;
    private final PermissionRepository permissionRepository;
    private final PersonneRepository personneRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {

        // Liste des permissions à garantir en base
        List<Permission> permissionsToEnsure = Arrays.asList(
                new Permission(null, "CREER_PROFIL", "Créer un profil", true, new HashSet<>()),
                new Permission(null, "MODIFIER_PROFIL", "Modifier un profil", true, new HashSet<>()),
                new Permission(null, "SUPPRIMER_PROFIL", "Supprimer un profil", true, new HashSet<>()),
                new Permission(null, "LISTER_PROFIL", "Lister les profils", true, new HashSet<>()),
                new Permission(null, "TROUVER_PROFIL", "Trouver profil par personne", true, new HashSet<>()),
                new Permission(null, "ATTRIBUER_PROFIL", "Attribuer un profil à une personne", true, new HashSet<>()),
                new Permission(null, "RETIRER_PROFIL", "Retirer un profil d’une personne", true, new HashSet<>())
        );

        // Créer ou mettre à jour les permissions
        for (Permission p : permissionsToEnsure) {
            permissionRepository.findByNom(p.getNom())
                    .orElseGet(() -> permissionRepository.save(p));
        }

        // Charger toutes les permissions existantes
        List<Permission> allPermissions = permissionRepository.findAll();

        // Créer ou mettre à jour le profil SUPER_ADMIN
        Profil superAdminProfil = profilRepository.findByCode("SUPER_ADMIN")
                .map(existing -> {
                    existing.setPermissions(new HashSet<>(allPermissions)); // mise à jour si nouvelles permissions
                    return profilRepository.save(existing);
                })
                .orElseGet(() -> {
                    Profil profil = Profil.builder()
                            .code("SUPER_ADMIN")
                            .libelle("Super Administrateur")
                            .permissions(new HashSet<>(allPermissions))
                            .superAdmin(true)
                            .actif(true)
                            .build();
                    return profilRepository.save(profil);
                });

        // Créer la personne Super Admin si elle n’existe pas
        if (personneRepository.findByEmail("superadmin@system.com").isEmpty()) {
            Personne superAdminUser = Personne.builder()
                    .nom("Super")
                    .prenoms("Admin")
                    .email("superadmin@system.com")
                    .motDePasse(passwordEncoder.encode("superadmin123"))
                    .dateInscription(new Date())
                    .statut(true)
                    .profils(Set.of(superAdminProfil))
                    .build();

            personneRepository.save(superAdminUser);
        }
    }
}


