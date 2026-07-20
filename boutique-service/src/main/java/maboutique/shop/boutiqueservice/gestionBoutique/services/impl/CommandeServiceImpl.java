package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.CommandeNonAnnulableException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.ResourceNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.CommandeMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitCommandeMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.CommandeRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.ProduitRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.UsersRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.CommandeService;
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

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final UsersRepository userRepository;
    private final ProduitRepository produitRepository;
    private final CommandeMapper commandeMapper;
    private final ProduitCommandeMapper produitCommandeMapper;

    @Override
    @Transactional
    public CommandeDto creerCommande(CommandeRequestDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Utilisateur introuvable."
                ));

        Commande commande = new Commande();
        commande.setUser(user);
        commande.setNumeroCommande(genererNumeroCommande());
        commande.setAdresseLivraison(dto.getAdresseLivraison());
        commande.setModeLivraison(dto.getModeLivraison());
        commande.setStatut(StatutCommande.EN_ATTENTE);

        List<ProduitCommande> produits = dto.getProduits().stream()
                .map(req -> {
                    Produit produit = produitRepository.findById(req.getProduitId())
                            .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));
                    ProduitCommande pc = new ProduitCommande();
                    pc.setCommande(commande);
                    pc.setProduit(produit);
                    pc.setQuantite(req.getQuantite());
                    pc.setPrixUnitaire(produit.getPrix());
                    pc.setSousTotal(produit.getPrix()
                            .multiply(BigDecimal.valueOf(req.getQuantite())));
                    return pc;
                })
                .toList();

        commande.setProduits(produits);

        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toDto(saved);
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
    public List<CommandeDto> listerCommandesParUser(UUID userId) {
        return commandeMapper.toDto(commandeRepository.findByUserId(userId));
    }

    @Override
    public List<CommandeDto> listerToutesCommandes() {
        return commandeMapper.toDto(commandeRepository.findAll());
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
    public void annulerCommande(UUID id) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable"));

        if (!commande.isAnnulable()) {
            throw new CommandeNonAnnulableException("Cette commande ne peut plus être annulée.");
        }

        commande.setStatut(StatutCommande.ANNULEE);
        commandeRepository.save(commande);
    }
}

