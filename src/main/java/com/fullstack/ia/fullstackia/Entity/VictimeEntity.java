package com.fullstack.ia.fullstackia.Entity;
import com.fullstack.ia.fullstackia.Enum.Arme;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter

public class VictimeEntity {
    // Getters et Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pour auto-générer l'ID
    private Long id;

    @Enumerated(EnumType.STRING)
    private Arme arme;

    private String lieu;

    private String description;
    // Constructeurs
    public VictimeEntity() {}

    public VictimeEntity(Arme arme, String lieu, String description) {
        this.arme = arme;
        this.lieu = lieu;
        this.description = description;
    }

}
