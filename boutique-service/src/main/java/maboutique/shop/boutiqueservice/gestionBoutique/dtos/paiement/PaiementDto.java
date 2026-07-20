package maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutPaiement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class PaiementDto {

    private UUID id;

    private UUID commandeId;

    private BigDecimal montant;

    private StatutPaiement statut;

    private String methodePaiement;

    private String referencePaiement;

    private LocalDateTime datePaiement;
}
