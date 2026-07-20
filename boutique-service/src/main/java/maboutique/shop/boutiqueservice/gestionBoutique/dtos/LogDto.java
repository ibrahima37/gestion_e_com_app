package maboutique.shop.boutiqueservice.gestionBoutique.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LogDto {

    private UUID id;

    private String action;

    private String description;

    private String module;

    private Date dateCreation;

    private LocalDateTime dateAction;
}
