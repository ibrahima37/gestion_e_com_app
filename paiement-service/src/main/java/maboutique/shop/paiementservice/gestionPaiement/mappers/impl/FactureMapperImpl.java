package maboutique.shop.paiementservice.gestionPaiement.mappers.impl;

import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.FactureDto;
import maboutique.shop.paiementservice.gestionPaiement.entities.Facture;
import maboutique.shop.paiementservice.gestionPaiement.mappers.ints.FactureMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FactureMapperImpl implements FactureMapper {

    @Override
    public FactureDto toDto(Facture facture) {

        if (facture ==null){
            return null;
        }

        return FactureDto.builder()
                .id(facture.getId())
                .numero(facture.getNumero())
                .dateEmission(facture.getDateEmission())
                .montantSousTotal(facture.getMontantSousTotal() != null ? facture.getMontantSousTotal().doubleValue() : 0.0)
                .montantTVA(facture.getMontantTVA() != null ? facture.getMontantTVA().doubleValue() : 0.0)
                .montantTotal(facture.getMontantTotal() != null ? facture.getMontantTotal().doubleValue() : 0.0)
                .statut(facture.getStatut())
                .methodePaiement(facture.getMethodePaiement())
                .commandeId(facture.getCommandeId())
                .details(facture.getDetails())
                .build();
    }

    @Override
    public Facture toEntity(FactureDto dto) {

        if (dto == null){
            return null;
        }

        Facture facture = new Facture();

        facture.setId(dto.getId());
        facture.setCommandeId(dto.getCommandeId());
        facture.setNumero(dto.getNumero());
        facture.setDateEmission(dto.getDateEmission());
        facture.setMontantSousTotal(BigDecimal.valueOf(dto.getMontantSousTotal()));
        facture.setMontantTVA(BigDecimal.valueOf(dto.getMontantTVA()));
        facture.setMontantTotal(BigDecimal.valueOf(dto.getMontantTotal()));
        facture.setStatut(dto.getStatut());
        facture.setMethodePaiement(dto.getMethodePaiement());
        facture.setDetails(dto.getDetails());

        return facture;
    }
}
