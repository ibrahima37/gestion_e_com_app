package maboutique.shop.paiementservice.gestionPaiement.dtos.paiement;

import lombok.*;
import maboutique.shop.paiementservice.gestionPaiement.enums.MethodePaiement;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private MethodePaiement methodePaiement;
    private String referencePaiement;
    private LocalDateTime datePaiement;
}