package maboutique.shop.commandeservice.gestionCommande.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDto {

    private UUID id;

    private String nom;

    private String description;

    private Boolean actif;

}