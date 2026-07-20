package maboutique.shop.boutiqueservice.gestionBoutique.dtos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RapportSystemeDto {

    private Long nombreUtilisateurs;

    private Long nombreLogs;

    private LocalDateTime dateGeneration;
}
