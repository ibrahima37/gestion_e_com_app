package maboutique.shop.boutiqueservice.gestionBoutique.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutFacture;

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

    private String methodePaiement;

    private UUID commandeId;

    private List<String> details;
}