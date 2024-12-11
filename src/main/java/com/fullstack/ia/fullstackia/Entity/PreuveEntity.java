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
public class PreuveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    // Type de preuve materiel, lettre, objet personnel
    private String type;
    // Permet valider ou invalider un temoiniage avec une preuve concrète
    private String incriminant;
    private String disculpant;


//    @ManyToOne
//    @JoinColumn(name="id")
//    private Personnages personnage;

}
