package maboutique.shop.commandeservice.gestionCommande.mappers.ints;

import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.ProduitPanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitPanier;

import java.util.List;

public interface ProduitPanierMapper {

    // Entité -> DTO
    ProduitPanierDto toDto(ProduitPanier produitPanier);

    // DTO -> Entité
    ProduitPanier toEntity(ProduitPanierDto dto);

    // Request -> Entité
    ProduitPanier toEntity(ProduitPanierRequestDto requestDto);

    // Liste d'entités -> Liste de DTOs
    List<ProduitPanierDto> toDtoList(List<ProduitPanier> produits);
}
