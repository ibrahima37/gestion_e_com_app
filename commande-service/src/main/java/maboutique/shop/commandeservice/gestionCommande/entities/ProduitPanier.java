package maboutique.shop.commandeservice.gestionCommande.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(exclude = "panier")
@Entity
@Table(name = "produitPaniers")
public class ProduitPanier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panier_id")
    private Panier panier;

    private UUID produitId;

    private Integer quantite;

}
