package maboutique.shop.commandeservice.gestionCommande.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.Commande;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitCommande;
import maboutique.shop.commandeservice.gestionCommande.enums.StatutCommande;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.CommandeMapper;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitCommandeMapper;
import maboutique.shop.commandeservice.gestionCommande.repositories.CommandeRepository;
import maboutique.shop.commandeservice.gestionCommande.services.ints.CommandeService;
import maboutique.shop.commonentities.gestionCommon.exceptions.CommandeNonAnnulableException;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final ProduitCommandeMapper produitCommandeMapper;

    @Override
    @Transactional
    public CommandeDto creerCommande(CommandeRequestDto dto) {

        Commande commande = commandeMapper.toEntity(dto);

        // Conversion des produits
        List<ProduitCommande> produits = dto.getProduits().stream()
                .map(produitCommandeMapper::toEntity)
                .collect(Collectors.toList());

        commande.setProduits(produits);
        commande.setNumeroCommande(genererNumeroCommande());
        commande.setStatut(StatutCommande.EN_ATTENTE);

        // Calcul du montant total
        BigDecimal total = produits.stream()
                .map(ProduitCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        commande.setMontantTotal(total);

        commande = commandeRepository.save(commande);
        return commandeMapper.toDto(commande);
    }

    private String genererNumeroCommande() {

        String prefix = "CMD";
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // Compter combien de commandes existent déjà pour aujourd'hui
        LocalDate today = LocalDate.now();
        Date start = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(today.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        long count = commandeRepository.countByDate(start, end);

        // Incrémenter le compteur
        long numero = count + 1;

        // Format avec 4 chiffres (0001, 0002…)
        String numeroPart = String.format("%04d", numero);

        return prefix + "-" + datePart + "-" + numeroPart;
    }

    @Override
    public CommandeDto trouverParId(UUID id) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));
        return commandeMapper.toDto(commande);
    }

    @Override
    public List<CommandeDto> listerCommandesParUtilisateur(UUID utilisateurId) {
        return commandeRepository.findByUtilisateurId(utilisateurId).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommandeDto> listerToutesCommandes() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommandeDto changerStatut(UUID id, StatutCommande nouveauStatut) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));
        commande.setStatut(nouveauStatut);
        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDto(saved);
    }

    @Override
    @Transactional
    public CommandeDto annulerCommande(UUID id) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));

        if (!commande.isAnnulable()) {
            throw new CommandeNonAnnulableException("Cette commande ne peut plus être annulée.");
        }

        commande.setStatut(StatutCommande.ANNULEE);
        commandeRepository.save(commande);

        return commandeMapper.toDto(commande);
    }

    @Override
    public CommandeDto mettreAJourLivraison(UUID id, String nouvelleAdresse, String nouveauMode) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        commande.setAdresseLivraison(nouvelleAdresse);
        commande.setModeLivraison(nouveauMode);

        commande = commandeRepository.save(commande);
        return commandeMapper.toDto(commande);
    }

    @Override
    public CommandeDto recalculerMontant(UUID id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        BigDecimal total = commande.getProduits().stream()
                .map(ProduitCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        commande.setMontantTotal(total);
        commande = commandeRepository.save(commande);

        return commandeMapper.toDto(commande);
    }

    @Override
    public List<CommandeDto> listerCommandesParStatut(StatutCommande statut) {
        return commandeRepository.findByStatut(statut).stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void supprimerCommande(UUID id) {
        commandeRepository.deleteById(id);
    }
}

