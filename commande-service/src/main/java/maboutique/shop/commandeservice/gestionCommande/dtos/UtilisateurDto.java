package maboutique.shop.commandeservice.gestionCommande.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class UtilisateurDto {

    private UUID id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    private String prenoms;

//    @ValidEmail
    private String email;

    private String motDePasse;

    //@ValidPhone
    private String telephone;

    private String adresse;

    private Date dateInscription;

    private Boolean statut;

    private Set<ProfilDto> profils;
    private LocalDate dateNaissance;
    private String preference;
}
