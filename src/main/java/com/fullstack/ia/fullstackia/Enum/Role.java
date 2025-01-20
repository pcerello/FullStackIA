package com.fullstack.ia.fullstackia.Enum;

public enum Role {
    VICTIME("victime"),
    ASSASSIN("assassin"),
    SUSPECT("suspect"),
    TEMOIN("temoin"),;

    private final String role;

    // Constructeur pour récuperer la descrition du sexe
    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
