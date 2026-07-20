package maboutique.shop.boutiqueservice.gestionBoutique.dtos.avis;

import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.BaseEntityDto;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class AvisDto extends BaseEntityDto {

    private UUID id;

    // Identifiant de l'utilisateur
    private UUID userId;
    private String nomUser;

    // Identifiant du produit
    private UUID produitId;
    private String nomProduit;

    private Integer note;

    private String commentaire;

    private LocalDate dateAvis;
}