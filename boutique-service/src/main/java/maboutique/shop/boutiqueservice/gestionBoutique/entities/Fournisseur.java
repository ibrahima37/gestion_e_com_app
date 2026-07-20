package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@DiscriminatorValue("FOURNISSEUR")
public class Fournisseur extends Personne {

    @OneToMany(
            mappedBy = "fournisseur",
            cascade = CascadeType.ALL
    )
    private List<Achat> achats = new ArrayList<>();
}
