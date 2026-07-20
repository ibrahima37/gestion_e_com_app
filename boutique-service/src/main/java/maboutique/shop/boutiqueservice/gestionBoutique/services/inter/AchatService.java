package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.achat.AchatRequestDto;

import java.util.List;
import java.util.UUID;

public interface AchatService {

    AchatDto creerAchat(AchatRequestDto dto, UUID fournisseurId);

    AchatDto modifierAchat(UUID id, AchatRequestDto dto);

    AchatDto trouverParId(UUID id);

    List<AchatDto> trouverTous();

    void supprimer(UUID id);
}
