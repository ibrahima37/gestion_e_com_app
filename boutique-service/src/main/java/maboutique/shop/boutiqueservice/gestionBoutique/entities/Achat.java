package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true, exclude = "produit")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "achats")
public class Achat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    @Column(nullable = false)
    private Integer quantiteAchetee;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal prixAchatUnitaire;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal;
}
