package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "avis")
public class Avis extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // Importé depuis common-entities

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit; // Importé depuis common-entities

    @Column(nullable = false)
    private Integer note;

    @Column(length = 1000)
    private String commentaire;

    private LocalDate dateAvis;
}
