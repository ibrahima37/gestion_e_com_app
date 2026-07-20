package maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CategorieRequestDto {

    @NotBlank(message = "Le nom de la catégorie est obligatoire")
    private String nomCategorie;

    private String image;

    private String description;
}
