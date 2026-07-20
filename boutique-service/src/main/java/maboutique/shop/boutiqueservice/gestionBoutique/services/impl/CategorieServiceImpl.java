package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;

import jakarta.persistence.EntityNotFoundException;
import maboutique.shop.boutiqueservice.gestionBoutique.exceptions.DuplicateResourceException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieDto;
import maboutique.shop.boutiqueservice.gestionBoutique.dtos.categorie.CategorieRequestDto;
import maboutique.shop.boutiqueservice.gestionBoutique.entities.Categorie;
import maboutique.shop.boutiqueservice.gestionBoutique.mappers.ints.CategorieMapper;
import maboutique.shop.boutiqueservice.gestionBoutique.repository.CategorieRepository;
import maboutique.shop.boutiqueservice.gestionBoutique.services.inter.CategorieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository repository;
    private final CategorieMapper mapper;

    @Override
    public CategorieDto creerCategorie(CategorieRequestDto dto) {

        if (repository.existsByNomCategorieIgnoreCase(dto.getNomCategorie())) {
            throw new RuntimeException(
                    "Cette catégorie existe déjà.");
        }

        Categorie categorie = mapper.toEntity(dto);

        categorie = repository.save(categorie);

        return mapper.toDto(categorie);
    }

    @Override
    public CategorieDto modifierCategorie(UUID id, CategorieDto dto) {

        Categorie categorie = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Catégorie introuvable avec l'id : " + id));

        // Vérification doublon du nom
        if (!categorie.getNomCategorie().equalsIgnoreCase(dto.getNomCategorie())
                && repository.existsByNomCategorieIgnoreCase(dto.getNomCategorie())) {

            throw new DuplicateResourceException(
                    "La catégorie '" + dto.getNomCategorie() + "' existe déjà."
            );
        }

        mapper.updateEntityFromDto(dto, categorie);

        categorie = repository.save(categorie);

        return mapper.toDto(categorie);
    }

    @Override
    public void supprimerCategorie(UUID id) {

        Categorie categorie = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Catégorie introuvable."));

        repository.delete(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorieDto> listerCategories() {

        return mapper.toDtoList(repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CategorieDto rechercherParId(UUID id) {

        Categorie categorie = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Catégorie introuvable."));

        return mapper.toDto(categorie);
    }
}