package maboutique.shop.utilisateurservice.gestionUtilisateur.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@DiscriminatorValue("USER")
public class Utilisateur extends Personne {

    private LocalDate dateNaissance;
    private String preference;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Avis> avis = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Commande> commandes = new ArrayList<>();
}
