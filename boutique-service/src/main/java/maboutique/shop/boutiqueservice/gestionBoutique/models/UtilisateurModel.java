package maboutique.shop.boutiqueservice.gestionBoutique.models;

import lombok.*;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.CompteDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.PersonneDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.ProfilDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.UserDto;

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
    private UserDto users;
}
