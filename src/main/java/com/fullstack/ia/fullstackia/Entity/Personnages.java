package com.fullstack.ia.fullstackia.Entity;

import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class Personnages {


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



    public Personnages() {}

    public Personnages(String nom, String prenom, int age, Sexe sexe, Role role, String caractere, String alibi, String mobile) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.role = role;
        this.caractere = caractere;
        this.alibi = alibi;
        this.mobile = mobile;
    }



}
