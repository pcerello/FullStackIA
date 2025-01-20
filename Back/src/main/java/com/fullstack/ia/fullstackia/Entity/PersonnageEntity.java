package com.fullstack.ia.fullstackia.Entity;

import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonnageEntity {


    // un nom, prénom, age, sexe, role, caractère, alibi, mobile.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    private Sexe sexe;
    private Role role;
    private String caractere;
    private String alibi;
    private String mobile;


    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private ScenarioEntity scenario;

    public PersonnageEntity(String nom, String prenom, int age, Sexe sexe, Role role,
                            String caractere, String alibi, String mobile, ScenarioEntity scenario) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.role = role;
        this.caractere = caractere;
        this.alibi = alibi;
        this.mobile = mobile;
        this.scenario = scenario;
    }


}