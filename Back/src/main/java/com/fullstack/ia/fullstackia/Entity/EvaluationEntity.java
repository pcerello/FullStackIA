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

public class EvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private String description;

    @Lob
    private Long timer;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private ScenarioEntity scenario;
}
