package maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.ProfilCreationDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.ProfilDto;

import java.util.List;
import java.util.UUID;

public interface ProfilService {

    ProfilDto creerProfil(ProfilCreationDto dto);

    ProfilDto modifierProfil(UUID id, ProfilCreationDto dto);

    void supprimerProfil(UUID id);

    ProfilDto trouverParId(UUID id);

    List<ProfilDto> listerProfils();

    void retirerProfil(UUID personneId, UUID profilId);
}
