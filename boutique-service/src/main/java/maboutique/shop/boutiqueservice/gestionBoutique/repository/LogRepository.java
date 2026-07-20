package maboutique.shop.boutiqueservice.gestionBoutique.repository;

import maboutique.shop.boutiqueservice.gestionBoutique.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface LogRepository extends JpaRepository<Log, UUID> {

    List<Log> findByPersonneId(UUID personneId);

    List<Log> findByModule(String module);

    List<Log> findByAction(String action);
}
