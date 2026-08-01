package maboutique.shop.commandeservice.gestionCommande.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "produits")
@SuperBuilder
@Entity
@Table(name = "paniers")
public class Panier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID utilisateurId;

    @OneToMany(
            mappedBy = "panier",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProduitPanier> produits = new ArrayList<>();

}
