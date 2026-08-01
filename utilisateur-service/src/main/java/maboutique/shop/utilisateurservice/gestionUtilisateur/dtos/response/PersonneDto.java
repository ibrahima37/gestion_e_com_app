package maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.utilisateurservice.gestionUtilisateur.utils.ValidEmail;
import maboutique.shop.utilisateurservice.gestionUtilisateur.utils.ValidPhone;
import maboutique.shop.commonentities.gestionCommon.dto.BaseEntityDto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class PersonneDto extends BaseEntityDto {

    private UUID id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    private String prenoms;

    @ValidEmail
    private String email;

    private String motDePasse;

    @ValidPhone
    private String telephone;

    private String adresse;

    private Date dateInscription;

    private Boolean statut;

    private Set<ProfilDto> profils;
}