package maboutique.shop.paiementservice.gestionPaiement.entities;

import jakarta.persistence.*;
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
@Entity
@Table(name = "paiements")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID commandeId;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    private MethodePaiement methodePaiement;
    private String referencePaiement;
    private LocalDateTime datePaiement;
}
