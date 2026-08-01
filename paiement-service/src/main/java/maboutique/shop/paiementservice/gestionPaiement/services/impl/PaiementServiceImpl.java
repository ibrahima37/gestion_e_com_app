package maboutique.shop.paiementservice.gestionPaiement.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import maboutique.shop.commonentities.gestionCommon.exceptions.PaiementRefuseException;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.CommandeDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.PaiementRequestDto;
import maboutique.shop.paiementservice.gestionPaiement.entities.Paiement;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutCommande;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;
import maboutique.shop.paiementservice.gestionPaiement.mappers.ints.PaiementMapper;
import maboutique.shop.paiementservice.gestionPaiement.models.CommandeClient;
import maboutique.shop.paiementservice.gestionPaiement.repository.PaiementRepository;
import maboutique.shop.paiementservice.gestionPaiement.services.ints.FactureService;
import maboutique.shop.paiementservice.gestionPaiement.services.ints.PaiementService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;
    private final CommandeClient commandeClient;
    private final PaiementMapper paiementMapper;
    private final FactureService factureService;

    @Override
    @Transactional
    public PaiementDto effectuerPaiement(PaiementRequestDto dto) {

        // Vérifier que la commande existe et récupérer ses détails
        CommandeDto commande = commandeClient.obtenirCommande(dto.getCommandeId());
        if (commande == null) {
            throw new ResourceNotFoundException("Commande inexistante");
        }

        // Vérifier que le montant correspond au montant de la commande
        if (dto.getMontant().compareTo(commande.getMontantTotal()) != 0) {
            throw new PaiementRefuseException("Le montant du paiement ne correspond pas au montant de la commande.");
        }

        // Créer l'entité Paiement
        Paiement paiement = paiementMapper.toEntity(dto);
        paiement.setStatut(StatutPaiement.EN_ATTENTE);
        paiement.setDatePaiement(LocalDateTime.now());

        // Sauvegarder le paiement
        paiementRepository.save(paiement);

        factureService.genererFacture(dto.getCommandeId());

        // Mettre à jour la commande (ex: statut payé)
        commandeClient.changerStatut(dto.getCommandeId(), StatutCommande.PAYEE);

        return paiementMapper.toDto(paiement);
    }

    @Override
    public List<PaiementDto> listerPaiements() {
        return paiementRepository.findAll()
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public List<PaiementDto> listerParCommande(UUID commandeId) {
        return paiementRepository.findByCommandeId(commandeId)
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public List<PaiementDto> listerParStatut(StatutPaiement statut) {
        return paiementRepository.findByStatut(statut)
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }

    @Override
    public List<PaiementDto> listerParClient(UUID clientId) {
        return paiementRepository.findByClientId(clientId)
                .stream()
                .map(paiementMapper::toDto)
                .toList();
    }
}

