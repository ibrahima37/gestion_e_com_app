package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.avis.AvisDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Avis;

import java.util.List;

public interface AvisMapper {

    AvisDto toDto(Avis entity);

    Avis toEntity(AvisDto dto);

    List<AvisDto> toDtoList(List<Avis> entities);

    List<Avis> toEntityList(List<AvisDto> dtos);
}
