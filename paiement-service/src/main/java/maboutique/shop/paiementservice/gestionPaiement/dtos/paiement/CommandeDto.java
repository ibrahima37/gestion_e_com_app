package maboutique.shop.paiementservice.gestionPaiement.dtos.paiement;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.MethodePaiement;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;

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
//    private StatutCommande statut;
//    private List<ProduitCommandeDto> produits;
    private BigDecimal montant;
    private BigDecimal montantTotal;
    private BigDecimal montantLivraison;
    private MethodePaiement methodePaiement;
    private StatutPaiement statutPaiement;
    private String adresseLivraison;
    private String modeLivraison;
    private String suiviCommande;

}