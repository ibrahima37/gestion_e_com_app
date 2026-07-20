package maboutique.shop.boutiqueservice.gestionBoutique.services.inter;

import maboutique.shop.boutiqueservice.gestionBoutique.dtos.commande.CommandeDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.panier.PanierRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.produit.ProduitPanierRequestDto;

import java.util.List;
import java.util.UUID;

public interface PanierService {

    PanierDto creerPanier(PanierRequestDto dto);

    PanierDto ajouterProduit(UUID panierId, ProduitPanierRequestDto produitDto);

    PanierDto retirerProduit(UUID panierId, UUID produitPanierId);

    PanierDto trouverParId(UUID id);

    List<PanierDto> listerPaniersParUser(UUID userId);

    void supprimerPanier(UUID id);

    CommandeDto validerPanier(UUID panierId);
}

