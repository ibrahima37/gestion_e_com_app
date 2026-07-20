package maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement;

import lombok.*;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutPaiement;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaiementRequestDto {

    private UUID commandeId;

    private BigDecimal montant;

    private StatutPaiement statut;

    private String methodePaiement;

    private String referencePaiement;
}