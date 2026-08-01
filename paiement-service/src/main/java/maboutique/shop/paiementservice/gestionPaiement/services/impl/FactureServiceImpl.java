package maboutique.shop.paiementservice.gestionPaiement.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import maboutique.shop.commonentities.gestionCommon.exceptions.ResourceNotFoundException;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.CommandeDto;
import maboutique.shop.paiementservice.gestionPaiement.dtos.paiement.FactureDto;
import maboutique.shop.paiementservice.gestionPaiement.entities.Facture;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutFacture;
import maboutique.shop.paiementservice.gestionPaiement.enums.StatutPaiement;
import maboutique.shop.paiementservice.gestionPaiement.mappers.ints.FactureMapper;
import maboutique.shop.paiementservice.gestionPaiement.models.CommandeClient;
import maboutique.shop.paiementservice.gestionPaiement.models.PdfGenerator;
import maboutique.shop.paiementservice.gestionPaiement.repository.FactureRepository;
import maboutique.shop.paiementservice.gestionPaiement.services.ints.FactureService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final  FactureMapper factureMapper;
    private final CommandeClient commandeClient;
    private final JavaMailSender mailSender;

    @Override
    public FactureDto genererFacture(UUID commandeId) {

        CommandeDto commande = commandeClient.getCommandeById(commandeId);

        if (commande.getStatutPaiement() != StatutPaiement.EFFECTUE) {
            throw new IllegalStateException("Impossible de générer une facture : commande non payée.");
        }

        Facture facture = Facture.builder()
                .numero(genererNumeroFacture())
                .dateEmission(LocalDate.now())
                .montantSousTotal(commande.getMontantTotal())
                .montantTVA(commande.getMontant().multiply(BigDecimal.valueOf(0.18)))
                .montantTotal(commande.getMontant().multiply(BigDecimal.valueOf(1.18)))
                .statut(StatutFacture.BROUILLON)
                .methodePaiement(commande.getMethodePaiement())
                .commandeId(commandeId)
                .details(List.of("Commande n°" + commande.getNumeroCommande()))
                .build();

        factureRepository.save(facture);

        return factureMapper.toDto(facture);
    }

    private String genererNumeroFacture() {
        String annee = String.valueOf(LocalDate.now().getYear());
        long compteur = factureRepository.count() + 1;
        return String.format("FAC-%s-%04d", annee, compteur);
    }

    @Override
    public FactureDto obtenirDetails(UUID factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(()-> new ResourceNotFoundException("Facture introuvable"));
        return factureMapper.toDto(facture);
    }

    @Override
    public byte[] telechargerFacture(UUID factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture introuvable"));

        // Générer un PDF avec iText ou autre lib
        return PdfGenerator.genererFacturePdf(facture);
    }

    @Override
    public void envoyerFacture(UUID factureId, String email) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture introuvable"));

        byte[] pdf = PdfGenerator.genererFacturePdf(facture);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Votre facture " + facture.getNumero());
            helper.setText("Veuillez trouver ci-joint votre facture.");
            helper.addAttachment("facture-" + facture.getNumero() + ".pdf", new ByteArrayResource(pdf));

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erreur lors de l'envoi de la facture par email", e);
        }
    }

    @Override
    public void validerFacture(UUID factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture introuvable"));

        facture.setStatut(StatutFacture.VALIDEE);
        factureRepository.save(facture);
    }

    @Override
    public byte[] imprimerFacture(UUID factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture introuvable"));

        // Générer un PDF et l’envoyer vers une imprimante (ici on retourne juste le PDF)
        return PdfGenerator.genererFacturePdf(facture);
    }

    @Override
    public List<FactureDto> listerFactures() {
        return factureRepository.findAll()
                .stream()
                .map(factureMapper::toDto)
                .toList();
    }

    @Override
    public void supprimerFacture(UUID factureId) {
        Facture facture = factureRepository.findById(factureId)
                .orElseThrow(() -> new ResourceNotFoundException("Facture introuvable"));
        factureRepository.delete(facture);
    }

    @Override
    public FactureDto trouverParId(UUID factureId) {
        return factureMapper.toDto(
                factureRepository.findById(factureId)
                        .orElseThrow(() -> new ResourceNotFoundException("Facture introuvable"))
        );
    }

    @Override
    public List<FactureDto> trouverParUtilisateur(UUID utilisateurId) {
        return factureRepository.findByUtilisateurId(utilisateurId)
                .stream()
                .map(factureMapper::toDto)
                .toList();
    }

    @Override
    public List<FactureDto> trouverParCommande(UUID commandeId) {
        return factureRepository.findByCommandeId(commandeId)
                .stream()
                .map(factureMapper::toDto)
                .toList();
    }

    @Override
    public List<FactureDto> trouverParStatut(StatutFacture statut) {
        return factureRepository.findByStatut(statut)
                .stream()
                .map(factureMapper::toDto)
                .toList();
    }
}
