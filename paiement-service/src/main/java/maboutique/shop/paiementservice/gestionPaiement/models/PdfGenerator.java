package maboutique.shop.paiementservice.gestionPaiement.models;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import maboutique.shop.paiementservice.gestionPaiement.entities.Facture;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

public class PdfGenerator {

    public static byte[] genererFacturePdf(Facture facture) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Titre
            document.add(new Paragraph("FACTURE")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18));

            // Numéro et date
            document.add(new Paragraph("Numéro : " + facture.getNumero()));
            document.add(new Paragraph("Date d'émission : " + facture.getDateEmission()));

            // Commande liée
            document.add(new Paragraph("Commande ID : " + facture.getCommandeId()));

            // Tableau des montants
            Table table = new Table(2);
            table.addCell("Sous-total");
            table.addCell(formatMontant(facture.getMontantSousTotal()));

            table.addCell("TVA");
            table.addCell(formatMontant(facture.getMontantTVA()));

            table.addCell("Total");
            table.addCell(formatMontant(facture.getMontantTotal()));

            document.add(table);

            // Statut et méthode de paiement
            document.add(new Paragraph("Statut : " + facture.getStatut()));
            document.add(new Paragraph("Méthode de paiement : " + facture.getMethodePaiement()));

            // Détails
            document.add(new Paragraph("Détails :"));
            facture.getDetails().forEach(detail -> document.add(new Paragraph("- " + detail)));

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF de la facture", e);
        }
    }

    private static String formatMontant(BigDecimal montant) {
        return montant != null ? montant.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + " €" : "0.00 €";
    }
}

