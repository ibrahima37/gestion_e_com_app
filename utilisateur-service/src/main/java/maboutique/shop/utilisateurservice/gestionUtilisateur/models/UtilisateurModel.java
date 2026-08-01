package maboutique.shop.utilisateurservice.gestionUtilisateur.models;

import lombok.*;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.CompteDto;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PersonneDto;import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.ProfilDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilisateurModel {

    private String username;
    private String password;
    private Boolean isPasswordReset;
    private String name;
    private Boolean actif;
    private List<String> roles;
    private ProfilDto profil;
    private CompteDto compte;
    private PersonneDto personnes;
}
