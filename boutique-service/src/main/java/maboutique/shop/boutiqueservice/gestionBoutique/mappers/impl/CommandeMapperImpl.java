package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.CommandeMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitCommandeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandeMapperImpl implements CommandeMapper {

    private final ProduitCommandeMapper produitCommandeMapper;

    @Override
    public CommandeDto toDto(Commande entity) {
        if (entity == null) return null;

        CommandeDto dto = new CommandeDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setNomUser(entity.getUser() != null ? entity.getUser().getNom() : null);
        dto.setEmailUser(entity.getUser() != null ? entity.getUser().getEmail() : null);
        dto.setNumeroCommande(entity.getNumeroCommande());
        dto.setStatut(entity.getStatut());

        dto.setProduits(produitCommandeMapper.toDto(entity.getProduits()));

        // Calcul du montant total
        BigDecimal montantTotal = entity.getProduits().stream()
                .map(ProduitCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setMontantTotal(montantTotal);

        dto.setMontantLivraison(entity.getMontantLivraison());
        dto.setAdresseLivraison(entity.getAdresseLivraison());
        dto.setModeLivraison(entity.getModeLivraison());
        dto.setSuiviCommande(entity.getSuiviCommande());

        return dto;
    }

    @Override
    public List<CommandeDto> toDto(List<Commande> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Commande toEntity(CommandeRequestDto dto, User user, List<ProduitCommande> produits) {
        if (dto == null) return null;

        Commande commande = new Commande();
        commande.setUser(user);
        commande.setProduits(produits);
        commande.setAdresseLivraison(dto.getAdresseLivraison());
        commande.setModeLivraison(dto.getModeLivraison());
        commande.setStatut(StatutCommande.EN_ATTENTE);

        return commande;
    }
}

