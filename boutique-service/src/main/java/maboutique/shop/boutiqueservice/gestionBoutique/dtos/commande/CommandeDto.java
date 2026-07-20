package maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitCommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class CommandeDto extends BaseEntityDto {

    private UUID id;

    private UUID userId;
    private String nomUser;
    private String emailUser;

    private String numeroCommande;

    private StatutCommande statut;

    private List<ProduitCommandeDto> produits;

    private BigDecimal montantTotal;

    private BigDecimal montantLivraison;

    private String adresseLivraison;

    private String modeLivraison;

    private String suiviCommande;

}