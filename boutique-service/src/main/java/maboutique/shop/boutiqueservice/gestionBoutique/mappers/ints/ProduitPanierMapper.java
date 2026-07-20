package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Panier;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitPanier;

import java.util.List;

public interface ProduitPanierMapper {

    ProduitPanierDto toDto(ProduitPanier entity);

    List<ProduitPanierDto> toDto(List<ProduitPanier> entities);

    ProduitPanier toEntity(ProduitPanierRequestDto dto, Produit produit, Panier panier);

    List<ProduitPanier> toEntity(List<ProduitPanierRequestDto> dtos, Panier panier);
}
