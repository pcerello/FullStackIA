package com.fullstack.ia.fullstackia.Enum;

public enum Sexe {
    HOMME("homme"),
    FEMME("femme");

    private final String sexe;

    // Constructeur pour récuperer la descrition du sexe
    Sexe(String sexe) {
        this.sexe = sexe;
    }

    public String getSexe() {
        return sexe;
    }
}
