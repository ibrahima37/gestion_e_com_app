package maboutique.shop.commandeservice.gestionCommande.mappers.ints;


import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.Panier;

import java.util.List;

public interface PanierMapper {

    // Entité -> DTO
    PanierDto toDto(Panier panier);

    // Liste d'entités -> Liste de DTOs
    List<PanierDto> toDtoList(List<Panier> paniers);

    // Request -> Entité
    Panier toEntity(PanierRequestDto requestDto);

    // DTO -> Entité
    Panier toEntity(PanierDto dto);
}
