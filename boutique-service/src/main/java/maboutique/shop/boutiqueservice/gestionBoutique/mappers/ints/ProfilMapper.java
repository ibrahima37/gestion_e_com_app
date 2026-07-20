package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilCreationDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Profil;

public interface ProfilMapper {

    Profil toEntity(ProfilCreationDto dto);

    ProfilDto toDto(Profil profil);

    Profil toEntity(ProfilDto dto);

}