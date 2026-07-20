package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.compte.FournisseurDto;

import java.util.UUID;

public interface FournisseurService {

    FournisseurDto findById(UUID id);
}
