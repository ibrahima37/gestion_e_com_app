package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ConnexionDto {

    private String email;
    private String motDePasse;
}
