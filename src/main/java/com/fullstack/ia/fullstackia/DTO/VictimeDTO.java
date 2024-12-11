package com.fullstack.ia.fullstackia.DTO;

import com.fullstack.ia.fullstackia.Enum.Arme;
import lombok.Builder;

@Builder
public record VictimeDTO (
                            Arme arme,
                            String lieu,
                            String description
){ }
