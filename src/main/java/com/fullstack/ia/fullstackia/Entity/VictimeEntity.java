package com.fullstack.ia.fullstackia.Entity;
import com.fullstack.ia.fullstackia.Enum.Arme;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class VictimeEntity extends PersonnageEntity{

    @Enumerated(EnumType.STRING)
    private Arme arme;

    private String lieu;

    private String description;

    public VictimeEntity(
            String nom,
            String prenom,
            int age,
            Sexe sexe,
            Role role,
            String caractere,
            String alibi,
            String mobile,
            ScenarioEntity scenario,
            Arme arme,
            String lieu,
            String description
    ) {
        super(nom, prenom, age, sexe, role, caractere, alibi, mobile, scenario); // Appel au constructeur parent
        this.arme = arme;
        this.lieu = lieu;
        this.description = description;
    }

}