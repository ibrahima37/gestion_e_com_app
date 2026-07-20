package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = {"user", "produits"})
@SuperBuilder
@Entity
@Table(name = "paniers")
public class Panier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(
            mappedBy = "panier",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProduitPanier> produits = new ArrayList<>();

}
