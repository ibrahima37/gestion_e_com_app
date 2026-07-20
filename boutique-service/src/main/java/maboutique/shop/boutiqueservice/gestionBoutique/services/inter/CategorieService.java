package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieRequestDto;

import java.util.List;
import java.util.UUID;

public interface CategorieService {

    CategorieDto creerCategorie(CategorieRequestDto dto);

    CategorieDto modifierCategorie(UUID id, CategorieDto dto);

    void supprimerCategorie(UUID id);

    CategorieDto rechercherParId(UUID id);

    List<CategorieDto> listerCategories();
}
