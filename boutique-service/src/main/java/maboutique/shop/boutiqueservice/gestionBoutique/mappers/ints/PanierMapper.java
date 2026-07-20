package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Panier;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitPanier;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;

import java.util.List;

public interface PanierMapper {

    PanierDto toDto(Panier entity);

    List<PanierDto> toDto(List<Panier> entities);

    Panier toEntity(PanierRequestDto dto, User user, List<ProduitPanier> produits);
}
