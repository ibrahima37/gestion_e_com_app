package maboutique.shop.commandeservice.gestionCommande.dtos.commande;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

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
    private UUID utilisateurId;
    private String numeroCommande;
    private StatutCommande statut;
    private List<ProduitCommandeDto> produits;
    private BigDecimal montantTotal;
    private BigDecimal montantLivraison;
    private String adresseLivraison;
    private String modeLivraison;
    private String suiviCommande;

}