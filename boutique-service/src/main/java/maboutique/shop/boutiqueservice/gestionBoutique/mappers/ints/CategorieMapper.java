package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Categorie;

import java.util.List;

public interface CategorieMapper {

    Categorie toEntity(CategorieRequestDto dto);

    Categorie toEntity(CategorieDto dto);

    CategorieDto toDto(Categorie categorie);

    List<CategorieDto> toDtoList(List<Categorie> categories);

    void updateEntityFromDto(CategorieDto dto, Categorie categorie);
}