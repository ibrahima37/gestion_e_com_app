package maboutique.shop.paiementservice.gestionPaiement.mappers.ints;

import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementRequestDto;
import maboutique.shop.paiementservice.gestionPaiement.entities.Paiement;

public interface PaiementMapper {

    PaiementDto toDto(Paiement paiement);
    Paiement toEntity(PaiementRequestDto dto);
}

