package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Paiement;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutPaiement;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.PaiementRefuseException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.ResourceNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PaiementMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.CommandeRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.PaiementRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.PaiementService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final CommandeRepository commandeRepository;
    private final PaiementMapper paiementMapper;

    @Override
    @Transactional
    public PaiementDto effectuerPaiement(PaiementRequestDto dto) {
        // Vérifier que la commande existe
        Commande commande = commandeRepository.findById(dto.getCommandeId())
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));

        // Vérifier que le montant correspond au montant de la commande
        if (dto.getMontant().compareTo(commande.getMontantTotal()) != 0) {
            throw new PaiementRefuseException("Le montant du paiement ne correspond pas au montant de la commande.");
        }

        // Créer l'entité Paiement
        Paiement paiement = Paiement.builder()
                .commande(commande)
                .montant(dto.getMontant())
                .statut(dto.getStatut() != null ? dto.getStatut() : StatutPaiement.EN_ATTENTE)
                .methodePaiement(dto.getMethodePaiement())
                .referencePaiement(dto.getReferencePaiement())
                .datePaiement(LocalDateTime.now())
                .build();

        // Sauvegarder le paiement
        Paiement saved = paiementRepository.save(paiement);

        // Mettre à jour la commande
        commande.setStatut(StatutCommande.PAYEE);
        commandeRepository.save(commande);

        return paiementMapper.toDto(saved);
    }
}

