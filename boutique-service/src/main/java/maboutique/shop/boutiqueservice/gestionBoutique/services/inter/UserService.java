package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PersonneDto;import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDetailDto;import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;import java.util.List;
import java.util.UUID;

public interface UserService {

    List<PersonneDto> consulterUtilisateurs();

    void bloquerUtilisateur(UUID userId);

    void activerUtilisateur(UUID userId);

    UserDto mettreAJourProfil(UUID id, UserDto dto);

    UserDetailDto consulterProfil(UUID id);

//    UserDto findById(UUID id);
}
