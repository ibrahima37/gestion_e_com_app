package maboutique.shop.commonsecurity.gestionSecurity.interfaces;

import java.util.UUID;

public interface IPermission {
    UUID getId();
    String getNom();
    String getDescription();
    Boolean getActif();
}
