package maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class CategorieDto extends BaseEntityDto {

    private UUID id;
    private String nomCategorie;
    private String image;
    private String description;
}