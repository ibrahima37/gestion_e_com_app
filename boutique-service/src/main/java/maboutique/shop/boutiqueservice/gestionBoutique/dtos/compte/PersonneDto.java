package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.*;
import lombok.experimental.SuperBuilder;

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

    private String nom;

    private String prenoms;

    private String email;

    private String motDePasse;

    private String telephone;

    private String adresse;

    private Date dateInscription;

    private Boolean statut;

    private Set<ProfilDto> profils;
}