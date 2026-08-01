package maboutique.shop.commonsecurity.gestionSecurity.interfaces;

import java.util.Set;import java.util.UUID;
public interface IPersonnes {

    UUID getId();
    String getNom();
    String getPrenoms();
    String getEmail();
    String getMotDePasse();
    String getTelephone();
    String getAdresse();
    Boolean getStatut();              // cohérent avec ton entité Personne
    Set<IProfil> getProfils();
}
