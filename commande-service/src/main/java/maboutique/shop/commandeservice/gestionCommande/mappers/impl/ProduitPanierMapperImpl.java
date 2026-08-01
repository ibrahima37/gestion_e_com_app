package maboutique.shop.commandeservice.gestionCommande.mappers.impl;

import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitPanier;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitPanierMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProduitPanierMapperImpl implements ProduitPanierMapper {

    @Override
    public ProduitPanierDto toDto(ProduitPanier produitPanier) {

        if (produitPanier == null) return null;

        ProduitPanierDto dto = new ProduitPanierDto();
        dto.setId(produitPanier.getId());
        dto.setPanierId(produitPanier.getPanier() != null ? produitPanier.getPanier().getId() : null);
        dto.setProduitId(produitPanier.getProduitId());
        dto.setQuantite(produitPanier.getQuantite());
        // nomProduit et images doivent être enrichis via boutique-service
        return dto;
    }

    @Override
    public ProduitPanier toEntity(ProduitPanierDto dto) {

        if (dto == null) return null;

        ProduitPanier produitPanier = new ProduitPanier();
        produitPanier.setId(dto.getId());
        produitPanier.setProduitId(dto.getProduitId());
        produitPanier.setQuantite(dto.getQuantite());
        // panier sera injecté dans le service
        return produitPanier;
    }

    @Override
    public ProduitPanier toEntity(ProduitPanierRequestDto requestDto) {

        if (requestDto == null) return null;

        ProduitPanier produitPanier = new ProduitPanier();
        produitPanier.setProduitId(requestDto.getProduitId());
        produitPanier.setQuantite(requestDto.getQuantite());
        return produitPanier;
    }

    @Override
    public List<ProduitPanierDto> toDtoList(List<ProduitPanier> produits) {

        if (produits == null) return null;
        return produits.stream().map(this::toDto).collect(Collectors.toList());
    }
}

