package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;


import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Paiement;

public interface PaiementMapper {

    PaiementDto toDto(Paiement paiement);
    Paiement toEntity(PaiementRequestDto dto);
}

