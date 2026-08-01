package maboutique.shop.utilisateurservice.gestionUtilisateur.services.impl;

import lombok.RequiredArgsConstructor;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import maboutique.shop.utilisateurservice.gestionUtilisateur.dtos.response.PersonneDto;
import maboutique.shop.utilisateurservice.gestionUtilisateur.entities.Personne;
import maboutique.shop.utilisateurservice.gestionUtilisateur.mappers.ints.PersonneMapper;
import maboutique.shop.utilisateurservice.gestionUtilisateur.repository.PersonneRepository;
import maboutique.shop.utilisateurservice.gestionUtilisateur.services.ints.PersonneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonneServiceImpl implements PersonneService {

    private final PersonneRepository personneRepository;
    private final PersonneMapper personneMapper;

    @Override
    public PersonneDto trouverParId(UUID id) {
        Personne personne = personneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personne introuvable"));
        return personneMapper.toDto(personne);
    }

    @Override
    public List<PersonneDto> trouverTous() {
        return personneRepository.findAll()
                .stream()
                .map(personneMapper::toDto)
                .toList();
    }

    @Override
    public PersonneDto modifier(UUID id, PersonneDto dto) {
        Personne personne = personneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personne introuvable"));

        // Mise à jour des champs
        personne.setNom(dto.getNom());
        personne.setPrenoms(dto.getPrenoms());
        personne.setEmail(dto.getEmail());
        personne.setTelephone(dto.getTelephone());
        personne.setAdresse(dto.getAdresse());
        personne.setStatut(dto.getStatut());

        personneRepository.save(personne);
        return personneMapper.toDto(personne);
    }

    @Override
    public void supprimer(UUID id) {
        Personne personne = personneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personne introuvable"));
        personneRepository.delete(personne);
    }

    @Override
    public PersonneDto rechercherParEmail(String email) {

        Personne personne = personneRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Personne introuvable"));
        return personneMapper.toDto(personne);
    }
}
