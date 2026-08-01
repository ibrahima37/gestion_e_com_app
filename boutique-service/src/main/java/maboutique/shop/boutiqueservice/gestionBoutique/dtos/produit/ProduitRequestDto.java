package maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.boutiqueservice.gestionBoutique.utils.ValidProductCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class ProduitRequestDto {

    @NotBlank(message = "Le nom du produit est obligatoire")
    private String nomProduit;

    private String marque;
    private String modele;

    @ValidProductCode
    private String code;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private BigDecimal prixVente;

    private int stock;
    private String description;
    private String specification;
    private List<String> images;

    @NotNull(message = "La catégorie est obligatoire")
    private UUID categorieId;
}