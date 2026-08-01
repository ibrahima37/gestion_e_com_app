package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    private String token;

    private UtilisateurDto utilisateur;

    private List<ProfilDto> profils;

    private List<PermissionDto> permissions;
}
