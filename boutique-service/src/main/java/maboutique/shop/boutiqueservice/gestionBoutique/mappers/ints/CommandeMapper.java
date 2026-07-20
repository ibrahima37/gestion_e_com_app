package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Commande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.ProduitCommande;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;

import java.util.List;

public interface CommandeMapper {

    CommandeDto toDto(Commande entity);

    List<CommandeDto> toDto(List<Commande> entities);

    Commande toEntity(CommandeRequestDto dto, User user, List<ProduitCommande> produits);
}