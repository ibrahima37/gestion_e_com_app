package maboutique.shop.commandeservice.gestionCommande.mappers.ints;

import maboutique.shop.commandeservice.gestionCommande.dtos.commande.ProduitCommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.ProduitCommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.ProduitCommande;

import java.util.List;

public interface ProduitCommandeMapper {

    ProduitCommandeDto toDto(ProduitCommande produitCommande);

    ProduitCommande toEntity(ProduitCommandeDto dto);

    ProduitCommande toEntity(ProduitCommandeRequestDto requestDto);

    List<ProduitCommandeDto> toDtoList(List<ProduitCommande> produits);

}
