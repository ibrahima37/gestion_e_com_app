package maboutique.shop.utilisateurservice.gestionUtilisateur.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@ToString(exclude = "profils")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Permission extends maboutique.shop.commonentities.gestionCommon.entity.BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String nom;

    private String description;

    @Builder.Default
    private Boolean actif = true;

    @ManyToMany(mappedBy = "permissions")
    private Set<Profil> profils = new HashSet<>();

}
