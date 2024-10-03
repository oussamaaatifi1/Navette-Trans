package com.platformtrasnport.platformtransport.model.enul;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    EMPLOYE,
    EMPLOYEUR,
    ADMIN,
    PARTICULIER;

    @Override
    public String getAuthority() {
        return name(); // Return the role name
    }
}
