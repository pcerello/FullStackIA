package com.fullstack.ia.fullstackia.Entity;
import com.fullstack.ia.fullstackia.Enum.Arme;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
@Getter

public class VictimeEntity extends PersonnageEntity{
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

    public VictimeEntity(String nom, String prenom, int age, Sexe sexe, Role role,
                         String caractere, String alibi, String mobile,
                         Arme arme, String lieu, String description) {
        super(nom, prenom, age, sexe, role, caractere, alibi, mobile);
        this.arme = arme;
        this.lieu = lieu;
        this.description = description;
    }

}
