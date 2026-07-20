package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;//package maboutique.shop.gestion_boutique.services.impl;
//
//import org.springframework.transaction.annotation.Transactional;
//import lombok.RequiredArgsConstructor;
//import maboutique.shop.gestion_boutique.dtos.RapportCategorieMensuelleDto;
//import maboutique.shop.gestion_boutique.repository.CategorieRepository;
//import maboutique.shop.gestion_boutique.repository.CommandeRepository;
//import maboutique.shop.gestion_boutique.repository.ProduitRepository;
//import maboutique.shop.gestion_boutique.services.inter.StatistiqueCategorieMensuelleService;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class StatistiqueCategorieMensuelleServiceImpl implements StatistiqueCategorieMensuelleService {
//
//    private final ProduitRepository produitRepository;
//    private final CommandeRepository commandeRepository;
//    private final CategorieRepository categorieRepository;
//
//    @Override
//    public Integer calculerProduitsAjoutes(UUID categorieId, int mois, int annee) {
//
//        return produitRepository.compterProduitsAjoutes(categorieId, mois, annee);
//    }
//
//    @Override
//    public Integer calculerProduitsVendus(UUID categorieId, int mois, int annee) {
//
//        return commandeRepository.compterProduitsVendus(categorieId, mois, annee);
//    }
//
//    @Override
//    public Integer calculerStockRestant(UUID categorieId) {
//
//        return produitRepository.calculerStockRestant(categorieId);
//    }
//
//    @Override
//    public BigDecimal calculerChiffreAffaires(UUID categorieId, int mois, int annee) {
//
//        return commandeRepository.calculerChiffreAffaires(categorieId, mois, annee);
//    }
//
//    @Override
//    public BigDecimal calculerBenefice(UUID categorieId, int mois, int annee) {
//
//        BigDecimal chiffreAffaires =
//                calculerChiffreAffaires(categorieId, mois, annee);
//
//        BigDecimal cout =
//                produitRepository.calculerCoutProduitsVendus(categorieId, mois, annee);
//
//        return chiffreAffaires.subtract(cout);
//    }
//
//    @Override
//    public RapportCategorieMensuelleDto genererRapportCategorie(UUID categorieId,
//                                                                int mois,
//                                                                int annee) {
//
//        RapportCategorieMensuelleDto rapport =
//                new RapportCategorieMensuelleDto();
//
//        rapport.setCategorieId(categorieId);
//        rapport.setMois(mois);
//        rapport.setAnnee(annee);
//
//        rapport.setProduitsAjoutes(
//                calculerProduitsAjoutes(categorieId, mois, annee));
//
//        rapport.setProduitsVendus(
//                calculerProduitsVendus(categorieId, mois, annee));
//
//        rapport.setStockRestant(
//                calculerStockRestant(categorieId));
//
//        rapport.setChiffreAffaires(
//                calculerChiffreAffaires(categorieId, mois, annee));
//
//        rapport.setBenefice(
//                calculerBenefice(categorieId, mois, annee));
//
//        return rapport;
//    }
//}