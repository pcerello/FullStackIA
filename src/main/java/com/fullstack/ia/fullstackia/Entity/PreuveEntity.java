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
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    // Type de preuve materiel, lettre, objet personnel
    @Enumerated(EnumType.STRING)
    private PreuveMateriel preuveMateriel;

    // Permet valider ou invalider un temoiniage avec une preuve concrète
    private String incriminant;
    private String disculpant;

    // Chaque témoignage doit pouvoir être comparé au preuves matériels
    @OneToOne
    @JoinColumn(name="temoignage_id")
    private TemoignageEntity temoignage;

}
