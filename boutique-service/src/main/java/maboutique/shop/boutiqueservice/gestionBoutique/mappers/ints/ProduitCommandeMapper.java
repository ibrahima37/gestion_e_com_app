package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitCommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitCommandeRequestDto;

import java.util.List;

public interface ProduitCommandeMapper {

    ProduitCommandeDto toDto(ProduitCommande entity);

    List<ProduitCommandeDto> toDto(List<ProduitCommande> entities);

    ProduitCommande toEntity(ProduitCommandeRequestDto dto, Produit produit, Commande commande);

    List<ProduitCommande> toEntity(List<ProduitCommandeRequestDto> dtos, Commande commande);
}
