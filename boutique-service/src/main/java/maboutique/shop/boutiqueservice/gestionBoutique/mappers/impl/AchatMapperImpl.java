package maboutique.shop.boutiqueservice.gestionBoutique.mappers.impl;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Achat;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.AchatMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AchatMapperImpl implements AchatMapper {

    @Override
    public AchatDto toDto(Achat entity) {

        if(entity == null){
            return null;
        }

        return AchatDto.builder()
                .id(entity.getId())

                .produitId(entity.getProduit().getId())
                .nomProduit(entity.getProduit().getNomProduit())

                // Fournisseur (depuis common-entities)
                .fournisseurId(entity.getFournisseur().getId())
                .nomFournisseur(entity.getFournisseur().getNom() + " " + entity.getFournisseur().getPrenoms())

                .quantiteAchetee(entity.getQuantiteAchetee())
                .prixAchatUnitaire(entity.getPrixAchatUnitaire())
                .montantTotal(entity.getMontantTotal())

                .build();
    }

    @Override
    public Achat toEntity(AchatRequestDto dto){

        return Achat.builder()
                .quantiteAchetee(dto.getQuantiteAchetee())
                .prixAchatUnitaire(dto.getPrixAchatUnitaire())
                .montantTotal(
                        dto.getPrixAchatUnitaire()
                                .multiply(
                                        BigDecimal.valueOf(dto.getQuantiteAchetee())
                                )
                )
                .build();
    }



    @Override
    public List<AchatDto> toDto(List<Achat> entities){

        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
