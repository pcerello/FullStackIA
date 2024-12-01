package com.fullstack.ia.fullstackia.Dto;

import com.fullstack.ia.fullstackia.Enum.Arme;
import lombok.Builder;

@Builder
public record VictimeDto(
                            Arme arme,
                            String lieu,
                            String description
){ }
