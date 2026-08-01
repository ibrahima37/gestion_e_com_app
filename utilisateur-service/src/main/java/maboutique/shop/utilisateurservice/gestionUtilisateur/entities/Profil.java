package maboutique.shop.utilisateurservice.gestionUtilisateur.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"permissions","personnes"})
@Entity
@Table(name = "profils")
public class Profil extends maboutique.shop.commonentities.gestionCommon.entity.BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(unique = true, nullable = false)
    private String libelle;

    @Builder.Default
    private Boolean superAdmin = false;

    @Column(nullable = false)
    private Boolean actif = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "profil_permission",
            joinColumns = @JoinColumn(name = "profil_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Builder.Default
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "profils")
    private Set<Personne> personnes = new HashSet<>();
}
