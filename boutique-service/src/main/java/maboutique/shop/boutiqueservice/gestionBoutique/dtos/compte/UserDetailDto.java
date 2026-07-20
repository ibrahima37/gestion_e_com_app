package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.avis.AvisDto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class UserDetailDto extends PersonneDto {

    private UUID id;

    private String nom;

    private String prenoms;

    private String email;

    private String telephone;

    private String adresse;

    private Date dateInscription;

    private Boolean statut;

    private Set<ProfilDto> profils;

    private LocalDate dateNaissance;

    private String preference;

    private List<AvisDto> avis;
}
