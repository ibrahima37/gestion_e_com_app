package maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.impl;

import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.creation.CompteCreationDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.CompteDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Compte;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.CompteMapper;
import org.springframework.stereotype.Component;

@Component
public class CompteMapperImpl implements CompteMapper {

    @Override
    public CompteDto toDto(Compte entity) {
        if (entity == null) return null;

        return CompteDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .isActived(entity.getActived())
                .isPasswordReset(entity.getPasswordReset())
                .titulaireId(entity.getTitulaire() != null ? entity.getTitulaire().getId() : null)
                .premiereConnexion(entity.getPremiereConnexion())
                .derniereConnexion(entity.getDerniereConnexion())
                .derniereDeconnexion(entity.getDerniereDeconnexion())
                .build();
    }

    @Override
    public Compte toEntity(CompteDto dto) {
        if (dto == null) return null;

        Compte compte = new Compte();
        compte.setId(dto.getId());
        compte.setEmail(dto.getEmail());
        compte.setActived(dto.getIsActived());
        compte.setPasswordReset(dto.getIsPasswordReset());
        compte.setPremiereConnexion(dto.getPremiereConnexion());
        compte.setDerniereConnexion(dto.getDerniereConnexion());
        compte.setDerniereDeconnexion(dto.getDerniereDeconnexion());
        return compte;
    }

    @Override
    public Compte toEntity(CompteCreationDto dto) {
        if (dto == null) return null;

        Compte compte = new Compte();
        compte.setEmail(dto.getEmail());
        compte.setMotDePasse(dto.getMotDePasse());
        compte.setActived(true);
        compte.setPasswordReset(false);

        return compte;
    }
}
