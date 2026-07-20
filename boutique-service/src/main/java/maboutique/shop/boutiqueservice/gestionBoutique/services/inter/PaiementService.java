package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.paiement.PaiementRequestDto;

public interface PaiementService {

    PaiementDto effectuerPaiement(PaiementRequestDto dto);
}
