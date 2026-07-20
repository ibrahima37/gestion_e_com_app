package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Panier;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitPanier;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitPanierMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProduitPanierMapperImpl implements ProduitPanierMapper {

    @Override
    public ProduitPanierDto toDto(ProduitPanier entity) {
        if (entity == null) return null;

        ProduitPanierDto dto = new ProduitPanierDto();
        dto.setId(entity.getId());
        dto.setPanierId(entity.getPanier() != null ? entity.getPanier().getId() : null);
        dto.setQuantite(entity.getQuantite());

        Produit produit = entity.getProduit();
        if (produit != null) {
            dto.setProduitId(produit.getId());
            dto.setNomProduit(produit.getNomProduit());
            dto.setImages(produit.getImages());
        }


        return dto;
    }

    @Override
    public List<ProduitPanierDto> toDto(List<ProduitPanier> entities) {
        if (entities == null) return List.of();
        return entities.stream().map(this::toDto).toList();
    }

    @Override
    public ProduitPanier toEntity(ProduitPanierRequestDto dto, Produit produit, Panier panier) {
        if (dto == null) return null;

        ProduitPanier entity = new ProduitPanier();
        entity.setPanier(panier);
        entity.setProduit(produit);
        entity.setQuantite(dto.getQuantite());

        return entity;
    }

    @Override
    public List<ProduitPanier> toEntity(List<ProduitPanierRequestDto> dtos, Panier panier) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .map(dto -> toEntity(dto, dto.getProduitId() != null ? new Produit() : null, panier))
                .toList();
    }
}

