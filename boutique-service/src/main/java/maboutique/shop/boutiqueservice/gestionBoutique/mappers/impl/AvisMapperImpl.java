package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.AvisMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Avis;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Produit;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.avis.AvisDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AvisMapperImpl implements AvisMapper {

    @Override
    public AvisDto toDto(Avis entity) {

        if (entity == null) {
            return null;
        }

        return AvisDto.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .nomUser(entity.getUser() != null ? entity.getUser().getNom() : null)
                .produitId(entity.getProduit() != null ? entity.getProduit().getId() : null)
                .nomProduit(entity.getProduit() != null ? entity.getProduit().getNomProduit() : null)
                .note(entity.getNote())
                .commentaire(entity.getCommentaire())
                .dateAvis(entity.getDateAvis())
                .build();
    }

    @Override
    public Avis toEntity(AvisDto dto) {

        if (dto == null) {
            return null;
        }

        Avis avis = new Avis();

        avis.setId(dto.getId());
        avis.setNote(dto.getNote());
        avis.setCommentaire(dto.getCommentaire());
        avis.setDateAvis(dto.getDateAvis());

        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            avis.setUser(user);
        }

        if (dto.getProduitId() != null) {
            Produit produit = new Produit();
            produit.setId(dto.getProduitId());
            avis.setProduit(produit);
        }

        return avis;
    }

    @Override
    public List<AvisDto> toDtoList(List<Avis> entities) {

        List<AvisDto> dtos = new ArrayList<>();

        if (entities == null || entities.isEmpty()) {
            return dtos;
        }

        for (Avis avis : entities) {
            dtos.add(toDto(avis));
        }

        return dtos;
    }

    @Override
    public List<Avis> toEntityList(List<AvisDto> dtos) {

        List<Avis> entities = new ArrayList<>();

        if (dtos == null || dtos.isEmpty()) {
            return entities;
        }

        for (AvisDto dto : dtos) {
            entities.add(toEntity(dto));
        }

        return entities;
    }
}
