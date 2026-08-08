package maboutique.shop.utilisateurservice.gestionUtilisateur.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "comptes")
public class Compte extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    @Builder.Default
    private Boolean passwordReset = false;

    @Builder.Default
    private Boolean actived = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "titulaire_id",
            nullable = false,
            unique = true
    )
    private Personne titulaire;

    private LocalDateTime premiereConnexion;

    private LocalDateTime derniereConnexion;

    private LocalDateTime derniereDeconnexion;
}
