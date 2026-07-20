package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    private String token;

    private UserDto utilisateur;

    private List<ProfilDto> profils;

    private List<PermissionDto> permissions;
}
