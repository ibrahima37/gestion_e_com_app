package maboutique.shop.paiementservice.gestionPaiement.mappers.ints;

import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.FactureDto;
import maboutique.shop.paiementservice.gestionPaiement.entities.Facture;

public interface FactureMapper {

    FactureDto toDto(Facture facture);

    Facture toEntity(FactureDto dto);
}