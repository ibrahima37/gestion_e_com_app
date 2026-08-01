package maboutique.shop.utilisateurservice.gestionUtilisateur.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "profils")
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "personnes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ptype", discriminatorType = DiscriminatorType.STRING)
public class Personne extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nom;
    private String prenoms;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private Date dateInscription;
    private Boolean statut;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "personne_profil",
            joinColumns = @JoinColumn(name = "personne_id"),
            inverseJoinColumns = @JoinColumn(name = "profil_id")
    )
    private Set<Profil> profils = new HashSet<>();
}
