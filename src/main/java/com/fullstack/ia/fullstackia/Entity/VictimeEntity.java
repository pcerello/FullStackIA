package com.fullstack.ia.fullstackia.Entity;
import com.fullstack.ia.fullstackia.Enum.Arme;
import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VictimeEntity extends PersonnageEntity{

    @Enumerated(EnumType.STRING)
    private Arme arme;

    private String lieu;

    private String description;


}
