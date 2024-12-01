package com.fullstack.ia.fullstackia.Dto;

import com.fullstack.ia.fullstackia.Enum.Role;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import lombok.Builder;

@Builder
public record PersonnageDTO (String nom, String prenom, int age, Sexe sexe, Role role, String caractere, String alibi, String mobile) {

}
