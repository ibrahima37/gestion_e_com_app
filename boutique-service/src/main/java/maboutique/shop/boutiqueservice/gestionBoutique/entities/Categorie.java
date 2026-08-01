package maboutique.shop.boutiqueservice.gestionBoutique.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import maboutique.shop.commonentities.gestionCommon.entity.BaseEntity;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
//@SuperBuilder(toBuilder = true)
@Builder
@Entity
@Table(name = "categories")
public class Categorie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String nomCategorie;

    private String image;

    @Column(length = 1000)
    private String description;
}
