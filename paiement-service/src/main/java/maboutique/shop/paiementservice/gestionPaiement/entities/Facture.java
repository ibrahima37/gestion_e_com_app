package maboutique.shop.paiementservice.gestionPaiement.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;
import maboutique.shop.paiementservice.gestionPaiement.enums.MethodePaiement;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutFacture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@Entity
@Table(name = "factures")
public class Facture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String numero;

    private LocalDate dateEmission;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantSousTotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTVA;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Enumerated(EnumType.STRING)
    private StatutFacture statut;

    private MethodePaiement methodePaiement;
    private UUID commandeId;
    private UUID utilisateurId;

    @ElementCollection
    @CollectionTable(
            name = "facture_details",
            joinColumns = @JoinColumn(name = "facture_id")
    )
    @Column(name = "detail")
    private List<String> details = new ArrayList<>();
}
