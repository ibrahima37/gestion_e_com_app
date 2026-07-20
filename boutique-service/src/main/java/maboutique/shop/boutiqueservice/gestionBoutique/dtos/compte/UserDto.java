package maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class UserDto extends PersonneDto {

    private String nom;

    private String prenoms;

    private String email;

    private String telephone;

    private String adresse;

    private LocalDate dateNaissance;

    private String preference;
}