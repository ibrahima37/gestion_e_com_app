package maboutique.shop.paiementservice.gestionPaiement.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementRequestDto;
import maboutique.shop.paiementservice.gestionPaiement.entities.Paiement;
import maboutique.shop.paiementservice.gestionPaiement.mappers.ints.PaiementMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaiementMapperImpl implements PaiementMapper {

    @Override
    public PaiementDto toDto(Paiement paiement) {

        if (paiement == null){
            return null;
        }

        PaiementDto dto = new PaiementDto();
        dto.setId(paiement.getId());
        dto.setCommandeId(paiement.getCommandeId());
        dto.setMontant(paiement.getMontant());
        dto.setStatut(paiement.getStatut());
        dto.setMethodePaiement(paiement.getMethodePaiement());
        dto.setReferencePaiement(paiement.getReferencePaiement());
        dto.setDatePaiement(paiement.getDatePaiement());

        return dto;
    }

    @Override
    public Paiement toEntity(PaiementRequestDto dto) {

        if (dto == null){
            return null;
        }

        Paiement paiement = new Paiement();

        paiement.setCommandeId(dto.getCommandeId());
        paiement.setMontant(dto.getMontant());
        paiement.setMethodePaiement(dto.getMethodePaiement());
        paiement.setStatut(dto.getStatut());
        paiement.setReferencePaiement(dto.getReferencePaiement());
        paiement.setDatePaiement(dto.getDatePaiement());

        return paiement;
    }
}
