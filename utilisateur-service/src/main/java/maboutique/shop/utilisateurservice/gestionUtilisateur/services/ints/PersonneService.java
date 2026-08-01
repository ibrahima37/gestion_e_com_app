package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PersonneDto;

import java.util.List;
import java.util.UUID;

public interface PersonneService {

    PersonneDto trouverParId(UUID id);

    List<PersonneDto> trouverTous();

    PersonneDto modifier(UUID id, PersonneDto dto);

    void supprimer(UUID id);

    PersonneDto rechercherParEmail(String email);

}
