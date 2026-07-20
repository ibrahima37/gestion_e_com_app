package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Panier;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitPanier;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.PanierMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitPanierMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PanierMapperImpl implements PanierMapper {

    private final ProduitPanierMapper produitPanierMapper;

    @Override
    public PanierDto toDto(Panier entity) {
        if (entity == null) return null;

        PanierDto dto = new PanierDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
        dto.setNomUser(entity.getUser() != null ? entity.getUser().getNom() : null);

        dto.setProduits(produitPanierMapper.toDto(entity.getProduits()));

        // Calcul dynamique du montant total (prix actuel du produit)
        BigDecimal montantTotal = entity.getProduits().stream()
                .map(pp -> pp.getProduit().getPrix()
                        .multiply(BigDecimal.valueOf(pp.getQuantite())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setMontantTotal(montantTotal);

        return dto;
    }

    @Override
    public List<PanierDto> toDto(List<Panier> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public Panier toEntity(PanierRequestDto dto, User user, List<ProduitPanier> produits) {
        if (dto == null) return null;

        Panier panier = new Panier();
        panier.setUser(user);
        panier.setProduits(produits);

        return panier;
    }
}


