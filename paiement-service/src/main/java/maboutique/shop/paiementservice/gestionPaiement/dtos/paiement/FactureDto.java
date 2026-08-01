package maboutique.shop.paiementservice.gestionPaiement.dtos.paiement;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;
import maboutique.shop.paiementservice.gestionPaiement.enums.MethodePaiement;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutFacture;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class FactureDto extends BaseEntityDto {

    private UUID id;
    private String numero;
    private LocalDate dateEmission;
    private double montantSousTotal;
    private double montantTVA;
    private double montantTotal;
    private StatutFacture statut;
    private MethodePaiement methodePaiement;
    private UUID commandeId;
    private List<String> details;
}