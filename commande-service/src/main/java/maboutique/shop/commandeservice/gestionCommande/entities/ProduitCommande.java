package maboutique.shop.commandeservice.gestionCommande.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(exclude = "commande")
@Entity
@Table(name = "produit_commandes")
public class ProduitCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    private UUID produitId;

    @Column(nullable = false)
    private Integer quantite;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal prixUnitaire;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal sousTotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal prixAchatUnitaire;
}
