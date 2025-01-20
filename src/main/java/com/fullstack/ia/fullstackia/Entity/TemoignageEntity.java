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
    private long id;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name="temoin_id")
    // Personnage avec le role TEMOIN
    private PersonnageEntity temoin;

    @ManyToOne
    @JoinColumn(name="suspect_id")
    // Personnage avec le role TEMOIN
    private PersonnageEntity suspect;

    @ManyToOne
    @JoinColumn(name="scenario_id")
    private ScenarioEntity scenario;
}