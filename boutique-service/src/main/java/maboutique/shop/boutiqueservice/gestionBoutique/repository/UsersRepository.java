package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
}
