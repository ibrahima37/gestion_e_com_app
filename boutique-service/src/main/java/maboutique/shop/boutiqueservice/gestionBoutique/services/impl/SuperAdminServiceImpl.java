package maboutique.shop.boutiqueservice.gestionBoutique.services.impl;//package maboutique.shop.gestion_boutique.services.impl;
//
//import org.springframework.transaction.annotation.Transactional;
//import lombok.RequiredArgsConstructor;
//import maboutique.shop.gestion_boutique.dtos.LogDto;
//import maboutique.shop.gestion_boutique.dtos.RapportSystemeDto;
//import maboutique.shop.gestion_boutique.dtos.compte.SuperAdminDto;
//import maboutique.shop.gestion_boutique.entities.Personne;
//import maboutique.shop.gestion_boutique.entities.Permission;
//import maboutique.shop.gestion_boutique.entities.SuperAdmin;
//import maboutique.shop.gestion_boutique.mappers.ints.SuperAdminMapper;
//import maboutique.shop.gestion_boutique.repository.LogRepository;
//import maboutique.shop.gestion_boutique.repository.PermissionRepository;
//import maboutique.shop.gestion_boutique.repository.PersonneRepository;
//import maboutique.shop.gestion_boutique.repository.SuperAdminRepository;
//import maboutique.shop.gestion_boutique.services.inter.SuperAdminService;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class SuperAdminServiceImpl implements SuperAdminService {
//
//    private final SuperAdminRepository superAdminRepository;
//    private final PersonneRepository personneRepository;
//    private final LogRepository logRepository;
//    private final PermissionRepository permissionRepository;
//    private final SuperAdminMapper superAdminMapper;
//
//
//    @Override
//    public SuperAdminDto creerSuperAdmin(SuperAdminDto dto) {
//
//        SuperAdmin superAdmin = superAdminMapper.toEntity(dto);
//
//        superAdmin.setNiveauAcces(1);
//
//        superAdmin = superAdminRepository.save(superAdmin);
//
//        return superAdminMapper.toDto(superAdmin);
//    }
//
//
//    @Override
//    public SuperAdminDto modifierNiveauAcces(UUID id, int niveau) {
//
//        SuperAdmin superAdmin = superAdminRepository.findById(id)
//                .orElseThrow(() ->
//                        new RuntimeException("Super administrateur introuvable"));
//
//        superAdmin.setNiveauAcces(niveau);
//
//        superAdmin = superAdminRepository.save(superAdmin);
//
//        return superAdminMapper.toDto(superAdmin);
//    }
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<LogDto> consulterLogs() {
//
//        return logRepository.findAll()
//                .stream()
//                .map(log -> LogDto.builder()
//                        .id(log.getId())
//                        .action(log.getAction())
//                        .description(log.getDescription())
//                        .module(log.getModule())
//                        .dateCreation(log.getDateCreation())
//                        .dateAction(log.getDateAction())
//                        .build()
//                )
//                .toList();
//    }
//
//
//    @Override
//    public void gererPermissions(UUID utilisateurId, List<UUID> permissionIds) {
//
//        Personne personne = personneRepository.findById(utilisateurId)
//                .orElseThrow(() ->
//                        new RuntimeException("Utilisateur introuvable"));
//
//        List<Permission> permissions =
//                permissionRepository.findAllById(permissionIds);
//
//        personne.getPermissions().clear();
//        personne.getPermissions().addAll(permissions);
//
//        personneRepository.save(personne);
//    }
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public RapportSystemeDto genererRapports() {
//
//        RapportSystemeDto rapport = new RapportSystemeDto();
//
//        rapport.setNombreUtilisateurs(
//                personneRepository.count()
//        );
//
//        rapport.setNombreLogs(
//                logRepository.count()
//        );
//
//        rapport.setDateGeneration(
//                LocalDateTime.now()
//        );
//
//        return rapport;
//    }
//}