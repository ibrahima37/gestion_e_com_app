package maboutique.shop.commandeservice.gestionCommande.mappers.ints;


import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.Commande;

import java.util.List;

public interface CommandeMapper {

    CommandeDto toDto(Commande commande);

    List<CommandeDto> toDtoList(List<Commande> commandes);

    Commande toEntity(CommandeRequestDto requestDto);

    Commande toEntity(CommandeDto dto);

}