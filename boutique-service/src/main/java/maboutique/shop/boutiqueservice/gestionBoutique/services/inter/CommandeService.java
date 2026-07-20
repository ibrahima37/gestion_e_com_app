package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.enums.StatutCommande;

import java.util.List;
import java.util.UUID;

public interface CommandeService {

    CommandeDto creerCommande(CommandeRequestDto dto);

    CommandeDto trouverParId(UUID id);

    List<CommandeDto> listerCommandesParUser(UUID userId);

    List<CommandeDto> listerToutesCommandes();

    CommandeDto changerStatut(UUID id, StatutCommande nouveauStatut);

    void annulerCommande(UUID id);
}

