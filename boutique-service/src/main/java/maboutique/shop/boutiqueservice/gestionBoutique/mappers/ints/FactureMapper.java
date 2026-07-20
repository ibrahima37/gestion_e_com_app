package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.FactureDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Facture;

public interface FactureMapper extends EntityMapper<Facture, FactureDto> {

    FactureDto toDto(Facture entity);

    Facture toEntity(FactureDto dto);
}