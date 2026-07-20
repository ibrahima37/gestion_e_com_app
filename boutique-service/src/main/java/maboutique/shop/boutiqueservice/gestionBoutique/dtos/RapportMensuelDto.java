package maboutique.shop.boutiqueservice.gestionBoutique.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RapportMensuelDto {

    private int mois;

    private int annee;

    private Integer nombreAchats;

    private Integer nombreVentes;

    private Integer stockRestant;

    private BigDecimal chiffreAffaires;

    private BigDecimal benefice;

    private LocalDateTime dateGeneration;
}