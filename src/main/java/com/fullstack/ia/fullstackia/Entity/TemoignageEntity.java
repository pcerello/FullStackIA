package com.fullstack.ia.fullstackia.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemoignageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,unique = true, nullable = false)
    private long id;
    // Permet de donner des vrais ou fausse piste incriminante ou disculpant
    private String incriminant;
    private String disculpant;
    // Faire un enum de type de témoiniage, sur un bruit entendu, un ragot, quelque chose de vu ect
    private String type;
    // Donne la veracité du témoiniage
    private Boolean isTrue;

//    @ManyToOne
//    @JoinColumn(name="id")
//    private Personnages personnage;
}
