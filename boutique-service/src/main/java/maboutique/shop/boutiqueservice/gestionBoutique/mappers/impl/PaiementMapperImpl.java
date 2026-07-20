package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Paiement;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PaiementMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PaiementMapperImpl implements PaiementMapper {

    @Override
    public PaiementDto toDto(Paiement paiement) {

        if (paiement == null) return null;

        PaiementDto dto = PaiementDto.builder()
                .id(paiement.getId())
                .commandeId(paiement.getCommande() != null ? paiement.getCommande().getId() : null)
                .montant(paiement.getMontant())
                .statut(paiement.getStatut())
                .methodePaiement(paiement.getMethodePaiement())
                .referencePaiement(paiement.getReferencePaiement())
                .datePaiement(paiement.getDatePaiement())
                .build();

        return dto;
    }

    @Override
    public Paiement toEntity(PaiementRequestDto dto) {

        if (dto == null) return null;

        Paiement paiement = Paiement.builder()
                .montant(dto.getMontant())
                .statut(dto.getStatut())
                .methodePaiement(dto.getMethodePaiement())
                .referencePaiement(dto.getReferencePaiement())
                .datePaiement(LocalDateTime.now())
                .build();

        // Associer la commande par son ID
        if (dto.getCommandeId() != null) {
            Commande commande = new Commande();
            commande.setId(dto.getCommandeId());
            paiement.setCommande(commande);
        }

        return paiement;
    }
}
