package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProfilCreationDto {

    @NotBlank(message = "Le code est obligatoire")
    private String code;

    @NotBlank(message = "Le libellé est obligatoire")
    private String libelle;

    @Builder.Default
    private Boolean superAdmin = false;

    private Set<UUID> permissionIds = new HashSet<>();

}