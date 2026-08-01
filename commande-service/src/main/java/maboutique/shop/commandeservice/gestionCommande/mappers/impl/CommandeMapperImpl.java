package maboutique.shop.commandeservice.gestionCommande.mappers.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeDto;
import maboutique.shop.commandeservice.gestionCommande.dtos.commande.CommandeRequestDto;
import maboutique.shop.commandeservice.gestionCommande.entities.Commande;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.CommandeMapper;
import maboutique.shop.commandeservice.gestionCommande.mappers.ints.ProduitCommandeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommandeMapperImpl implements CommandeMapper {


    @Override
    public CommandeDto toDto(Commande commande) {

        if (commande == null) return null;

        CommandeDto dto = new CommandeDto();
        dto.setId(commande.getId());
        dto.setUtilisateurId(commande.getUtilisateurId());
        dto.setNumeroCommande(commande.getNumeroCommande());
        dto.setStatut(commande.getStatut());
        dto.setMontantTotal(commande.getMontantTotal());
        dto.setMontantLivraison(commande.getMontantLivraison());
        dto.setAdresseLivraison(commande.getAdresseLivraison());
        dto.setModeLivraison(commande.getModeLivraison());
        dto.setSuiviCommande(commande.getSuiviCommande());

        if (commande.getProduits() != null) {
            ProduitCommandeMapper produitMapper = new ProduitCommandeMapperImpl();
            dto.setProduits(
                    commande.getProduits().stream()
                            .map(produitMapper::toDto)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    @Override
    public List<CommandeDto> toDtoList(List<Commande> commandes) {

        if (commandes == null) return null;
        return commandes.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Commande toEntity(CommandeRequestDto requestDto) {

        if (requestDto == null) return null;

        Commande commande = new Commande();
        commande.setUtilisateurId(requestDto.getUtilisateurId());
        commande.setAdresseLivraison(requestDto.getAdresseLivraison());
        commande.setModeLivraison(requestDto.getModeLivraison());
        // Les produits seront mappés séparément dans le service
        return commande;
    }

    @Override
    public Commande toEntity(CommandeDto dto) {

        if (dto == null) return null;

        Commande commande = new Commande();
        commande.setId(dto.getId());
        commande.setUtilisateurId(dto.getUtilisateurId());
        commande.setNumeroCommande(dto.getNumeroCommande());
        commande.setStatut(dto.getStatut());
        commande.setMontantTotal(dto.getMontantTotal());
        commande.setMontantLivraison(dto.getMontantLivraison());
        commande.setAdresseLivraison(dto.getAdresseLivraison());
        commande.setModeLivraison(dto.getModeLivraison());
        commande.setSuiviCommande(dto.getSuiviCommande());

        if (dto.getProduits() != null) {
            ProduitCommandeMapper produitMapper = new ProduitCommandeMapperImpl();
            commande.setProduits(
                    dto.getProduits().stream()
                            .map(produitMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }

        return commande;
    }
}

