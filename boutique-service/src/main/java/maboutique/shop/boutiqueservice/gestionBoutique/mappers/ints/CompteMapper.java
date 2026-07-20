package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.CompteCreationDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.CompteDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Compte;

public interface CompteMapper extends EntityMapper<Compte, CompteDto> {

    CompteDto toDto(Compte entity);

    Compte toEntity(CompteDto dto);

    Compte toEntity(CompteCreationDto dto);
}
