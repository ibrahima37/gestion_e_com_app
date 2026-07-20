package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.AvisMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitResumeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.ProduitMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProduitMapperImpl implements ProduitMapper {

    private final AvisMapper avisMapper;

    @Override
    public ProduitDto toDto(Produit entity) {

        if (entity == null) {
            return null;
        }

        return ProduitDto.builder()
                .id(entity.getId())
                .nomProduit(entity.getNomProduit())
                .marque(entity.getMarque())
                .modele(entity.getModele())
                .code(entity.getCode())
                .prix(entity.getPrix())
                .stock(entity.getStock())
                .description(entity.getDescription())
                .specification(entity.getSpecification())
                .images(entity.getImages())
                .notation(entity.getNotation() != null ? entity.getNotation().floatValue() : 0F)
                .nombreAvis(entity.getNombreAvis())
                .categorieId(
                        entity.getCategories() != null
                                ? entity.getCategories().getId()
                                : null
                )
                .avis(
                        entity.getAvis() != null
                                ? avisMapper.toDtoList(entity.getAvis())
                                : new ArrayList<>()
                )
                .build();
    }

    @Override
    public ProduitResumeDto toResumeDto(Produit entity) {

        if (entity == null) {
            return null;
        }

        return ProduitResumeDto.builder()
                .id(entity.getId())
                .nomProduit(entity.getNomProduit())
                .marque(entity.getMarque())
                .prix(entity.getPrix())
                .images(entity.getImages())
                .notation(entity.getNotation() != null ? entity.getNotation().floatValue() : 0F)
                .nombreAvis(entity.getNombreAvis())
                .categorieId(
                        entity.getCategories() != null
                                ? entity.getCategories().getId()
                                : null
                )
                .nomCategorie(
                        entity.getCategories() != null
                                ? entity.getCategories().getNomCategorie()
                                : null
                )
                .build();
    }

    @Override
    public Produit toEntity(ProduitRequestDto dto) {

        if (dto == null) {
            return null;
        }

        Produit produit = new Produit();

        produit.setNomProduit(dto.getNomProduit());
        produit.setMarque(dto.getMarque());
        produit.setModele(dto.getModele());
        produit.setCode(dto.getCode());
        produit.setPrix(dto.getPrix());
        produit.setStock(dto.getStock());
        produit.setDescription(dto.getDescription());
        produit.setSpecification(dto.getSpecification());
        produit.setImages(dto.getImages());

        return produit;
    }

    @Override
    public List<ProduitResumeDto> toResumeDto(List<Produit> entities) {

        List<ProduitResumeDto> dtos = new ArrayList<>();

        if (entities == null || entities.isEmpty()) {
            return dtos;
        }

        for (Produit produit : entities) {
            dtos.add(toResumeDto(produit));
        }

        return dtos;
    }
}
