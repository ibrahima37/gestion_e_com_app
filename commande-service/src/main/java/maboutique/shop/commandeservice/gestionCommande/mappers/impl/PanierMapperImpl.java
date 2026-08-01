package maboutique.shop.commandeservice.gestionCommande.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.panier.PanierRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.Panier;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.PanierMapper;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitPanierMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PanierMapperImpl implements PanierMapper {

    @Override
    public PanierDto toDto(Panier panier) {

        if (panier == null) return null;

        PanierDto dto = new PanierDto();
        dto.setId(panier.getId());
        dto.setUserId(panier.getUtilisateurId());

        // montantTotal calculé dans le service, pas ici
        if (panier.getProduits() != null) {
            ProduitPanierMapper produitMapper = new ProduitPanierMapperImpl();
            dto.setProduits(
                    panier.getProduits().stream()
                            .map(produitMapper::toDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    @Override
    public List<PanierDto> toDtoList(List<Panier> paniers) {

        if (paniers == null) return null;
        return paniers.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Panier toEntity(PanierRequestDto requestDto) {

        if (requestDto == null) return null;

        Panier panier = new Panier();
        panier.setUtilisateurId(requestDto.getUtilisateurId());
        return panier;
    }

    @Override
    public Panier toEntity(PanierDto dto) {

        if (dto == null) return null;

        Panier panier = new Panier();
        panier.setId(dto.getId());
        panier.setUtilisateurId(dto.getUserId());

        if (dto.getProduits() != null) {
            ProduitPanierMapper produitMapper = new ProduitPanierMapperImpl();
            panier.setProduits(
                    dto.getProduits().stream()
                            .map(produitMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }

        return panier;
    }
}


