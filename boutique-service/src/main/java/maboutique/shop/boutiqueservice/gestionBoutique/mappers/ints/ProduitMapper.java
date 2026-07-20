package maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitResumeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;

import java.util.List;

public interface ProduitMapper{

    ProduitDto toDto(Produit entity);

    ProduitResumeDto toResumeDto(Produit entity);

    Produit toEntity(ProduitRequestDto dto);

    List<ProduitResumeDto> toResumeDto(List<Produit> entities);

}