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


    

}
