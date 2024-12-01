package com.fullstack.ia.fullstackia.Entity;

import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonnagesEntity {

    // un nom, prénom, age, sexe, role, caractère, alibi, mobile.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private int age;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String caractere;
    private String alibi;
    private String mobile;

    @OneToMany(mappedBy = "temoin", cascade = CascadeType.ALL)
    // Personnage avec le role TEMOIN
    private List<TemoignageEntity> temoignagesDonnes;

    @OneToMany(mappedBy = "suspect", cascade = CascadeType.ALL)
    // Accusation sur un suspect
    private List<TemoignageEntity> temoignagesRecus;
    
}
