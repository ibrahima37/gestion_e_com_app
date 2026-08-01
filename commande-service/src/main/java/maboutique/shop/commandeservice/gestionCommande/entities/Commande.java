package maboutique.shop.commandeservice.gestionCommande.entities;

import jakarta.persistence.*;
import lombok.*;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString(callSuper = true, exclude = "produits")
@Table(name = "commandes")
public class Commande extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID utilisateurId;

    @Column(unique = true)
    private String numeroCommande;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    @OneToMany(
            mappedBy = "commande",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProduitCommande> produits = new ArrayList<>();

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal montantTotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantLivraison;

    private String adresseLivraison;
    private String modeLivraison;
    private String suiviCommande;

    public boolean isAnnulable() {
        return this.statut == StatutCommande.EN_ATTENTE
                || this.statut == StatutCommande.EN_COURS;
    }
}
