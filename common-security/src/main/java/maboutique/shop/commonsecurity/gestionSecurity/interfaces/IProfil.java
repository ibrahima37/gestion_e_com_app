package maboutique.shop.commonsecurity.gestionSecurity.interfaces;

import java.util.Set;
import java.util.UUID;

public interface IProfil {

    UUID getId();
    String getLibelle();
    String getCode();
    Set<IPermission> getPermissions();
}
