package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitCommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitCommandeRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitCommandeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProduitCommandeMapperImpl implements ProduitCommandeMapper {

    @Override
    public ProduitCommandeDto toDto(ProduitCommande entity) {
        if (entity == null) return null;

        ProduitCommandeDto dto = new ProduitCommandeDto();
        dto.setId(entity.getId());
        dto.setCommandeId(entity.getCommande() != null ? entity.getCommande().getId() : null);
        dto.setQuantite(entity.getQuantite());
        dto.setPrixUnitaire(entity.getPrixUnitaire());
        dto.setSousTotal(entity.getSousTotal());
        dto.setPrixUnitaire(entity.getPrixAchatUnitaire());

        // Ici on récupère les infos du produit
        Produit produit = entity.getProduit();
        if (produit != null) {
            dto.setProduitId(produit.getId());
            dto.setNomProduit(produit.getNomProduit());
            dto.setImages(produit.getImages());
        }

        return dto;
    }

    @Override
    public List<ProduitCommandeDto> toDto(List<ProduitCommande> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public ProduitCommande toEntity(ProduitCommandeRequestDto dto, Produit produit, Commande commande) {
        if (dto == null) return null;

        ProduitCommande entity = new ProduitCommande();
        entity.setCommande(commande);
        entity.setProduit(produit);
        entity.setQuantite(dto.getQuantite());
        entity.setPrixUnitaire(produit.getPrix());
        entity.setSousTotal(produit.getPrix().multiply(BigDecimal.valueOf(dto.getQuantite())));

        return entity;
    }

    @Override
    public List<ProduitCommande> toEntity(List<ProduitCommandeRequestDto> dtos, Commande commande) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .map(
                        dto ->
                                toEntity(dto, dto.getProduitId() != null ? new Produit() : null, commande))
                .toList();
    }
}

