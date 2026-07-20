package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PersonneDto;

import java.util.List;
import java.util.UUID;

public interface PersonneService {

    PersonneDto trouverParId(UUID id);

    List<PersonneDto> trouverTous();

    PersonneDto modifier(UUID id, PersonneDto dto);

    void supprimer(UUID id);

    PersonneDto rechercherParEmail(String email);

}
