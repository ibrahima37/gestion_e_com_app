package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@DiscriminatorValue("USER")
public class User extends Personne {

    private LocalDate dateNaissance;
    private String preference;

    @OneToMany(mappedBy = "user")
    private List<Avis> avis = new ArrayList<>();
}
