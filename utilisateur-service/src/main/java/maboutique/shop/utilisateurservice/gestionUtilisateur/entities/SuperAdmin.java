package maboutique.shop.utilisateurservice.gestionUtilisateur.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@DiscriminatorValue("SUPER_ADMIN")
public class SuperAdmin extends Personne {
    private int niveauAcces;
}
