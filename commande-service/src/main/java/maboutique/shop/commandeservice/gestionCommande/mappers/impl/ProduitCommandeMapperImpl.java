package maboutique.shop.commandeservice.gestionCommande.mappers.impl;

import maboutique.shop.commandeservice.gestionCommande.dtos.commande.ProduitCommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.ProduitCommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitCommande;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitCommandeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProduitCommandeMapperImpl implements ProduitCommandeMapper {


    @Override
    public ProduitCommandeDto toDto(ProduitCommande produitCommande) {

        if (produitCommande == null) return null;

        ProduitCommandeDto dto = new ProduitCommandeDto();
        dto.setId(produitCommande.getId());
        dto.setCommandeId(produitCommande.getCommande() != null ? produitCommande.getCommande().getId() : null);
        dto.setProduitId(produitCommande.getProduitId());
        dto.setQuantite(produitCommande.getQuantite());
        dto.setPrixUnitaire(produitCommande.getPrixUnitaire());
        dto.setSousTotal(produitCommande.getSousTotal());
        dto.setPrixAchatUnitaire(produitCommande.getPrixAchatUnitaire());
        // nomProduit et images doivent être enrichis via boutique-service
        return dto;
    }

    @Override
    public ProduitCommande toEntity(ProduitCommandeDto dto) {

        if (dto == null) return null;

        ProduitCommande produitCommande = new ProduitCommande();
        produitCommande.setId(dto.getId());
        produitCommande.setProduitId(dto.getProduitId());
        produitCommande.setQuantite(dto.getQuantite());
        produitCommande.setPrixUnitaire(dto.getPrixUnitaire());
        produitCommande.setSousTotal(dto.getSousTotal());
        produitCommande.setPrixAchatUnitaire(dto.getPrixAchatUnitaire());
        return produitCommande;
    }

    @Override
    public ProduitCommande toEntity(ProduitCommandeRequestDto requestDto) {

        if (requestDto == null) return null;

        ProduitCommande produitCommande = new ProduitCommande();
        produitCommande.setProduitId(requestDto.getProduitId());
        produitCommande.setQuantite(requestDto.getQuantite());
        // prixUnitaire et sousTotal seront calculés dans le service
        return produitCommande;
    }

    @Override
    public List<ProduitCommandeDto> toDtoList(List<ProduitCommande> produits) {

        if (produits == null) return null;
        return produits.stream().map(this::toDto).collect(Collectors.toList());
    }
}

