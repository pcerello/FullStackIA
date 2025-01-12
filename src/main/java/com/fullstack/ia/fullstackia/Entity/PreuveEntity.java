package com.fullstack.ia.fullstackia.Entity;

import com.fullstack.ia.fullstackia.Enum.PreuveMateriel;
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
    private long id;
    // Type de preuve materiel, lettre, objet personnel
    private String type;

    private String description;

    // Chaque témoignage doit pouvoir être comparé au preuves matériels
    @OneToOne
    @JoinColumn(name="temoignage_id")
    private TemoignageEntity temoignage;

}