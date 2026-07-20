package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class AdminDto extends PersonneDto{

    private String departement;
}
