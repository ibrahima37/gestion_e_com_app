package maboutique.shop.boutiqueservice.gestionBoutique.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RapportCategorieMensuelleDto {

    private UUID categorieId;

    private int mois;

    private int annee;

    private Integer produitsAjoutes;

    private Integer produitsVendus;

    private Integer stockRestant;

    private BigDecimal chiffreAffaires;

    private BigDecimal benefice;
}
