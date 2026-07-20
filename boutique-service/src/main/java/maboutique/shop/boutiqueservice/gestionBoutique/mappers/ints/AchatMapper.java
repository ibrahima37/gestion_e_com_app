package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Achat;

import java.util.List;

public interface AchatMapper {

    AchatDto toDto(Achat entity);

    List<AchatDto> toDto(List<Achat> entities);

    Achat toEntity(AchatRequestDto dto);
}