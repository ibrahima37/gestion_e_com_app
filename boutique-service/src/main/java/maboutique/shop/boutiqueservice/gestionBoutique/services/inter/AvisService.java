package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.avis.AvisDto;

import java.util.List;
import java.util.UUID;

public interface AvisService {

    void ajouterAvis(UUID userId, UUID produitId, String commentaire);

    void modifierAvis(UUID avisId, String commentaire);

    void supprimerAvis(UUID avisId);

    List<AvisDto> consulterMesAvis(UUID userId);
}
