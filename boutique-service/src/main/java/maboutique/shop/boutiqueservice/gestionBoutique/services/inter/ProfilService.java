package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilCreationDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDetailDto;

import java.util.List;
import java.util.UUID;

public interface ProfilService {

    ProfilDto creerProfil(ProfilCreationDto dto);


    ProfilDto modifierProfil(UUID id, ProfilCreationDto dto);


    void supprimerProfil(UUID id);


    ProfilDto trouverParId(UUID id);


    List<ProfilDto> listerProfils();

    UserDetailDto attribuerProfil(UUID personneId, UUID profilId);


    void retirerProfil(UUID personneId, UUID profilId);
}
