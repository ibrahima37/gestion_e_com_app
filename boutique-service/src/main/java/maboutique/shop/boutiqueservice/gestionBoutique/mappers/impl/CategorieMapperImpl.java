package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Categorie;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.CategorieMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategorieMapperImpl implements CategorieMapper {

    @Override
    public Categorie toEntity(CategorieRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return Categorie.builder()
                .nomCategorie(dto.getNomCategorie())
                .image(dto.getImage())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public Categorie toEntity(CategorieDto dto) {

        if (dto == null) {
            return null;
        }

        return Categorie.builder()
                .id(dto.getId())
                .nomCategorie(dto.getNomCategorie())
                .image(dto.getImage())
                .description(dto.getDescription())
                .build();
    }

    @Override
    public CategorieDto toDto(Categorie categorie) {

        if (categorie == null) {
            return null;
        }

        return CategorieDto.builder()
                .id(categorie.getId())
                .nomCategorie(categorie.getNomCategorie())
                .image(categorie.getImage())
                .description(categorie.getDescription())
                .build();
    }

    @Override
    public List<CategorieDto> toDtoList(List<Categorie> categories) {

        if (categories == null) {
            return List.of();
        }

        return categories.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void updateEntityFromDto(CategorieDto dto, Categorie categorie) {

        if (dto == null || categorie == null) {
            return;
        }


        if (dto.getNomCategorie() != null) {
            categorie.setNomCategorie(dto.getNomCategorie());
        }


        if (dto.getImage() != null) {
            categorie.setImage(dto.getImage());
        }


        if (dto.getDescription() != null) {
            categorie.setDescription(dto.getDescription());
        }
    }
}
