package com.fullstack.ia.fullstackia.Dto;

import com.fullstack.ia.fullstackia.Enum.Sexe;
import com.fullstack.ia.fullstackia.Enum.Role;

public record PersonnagesDto(Long id, String nom, String prenom, int age, Sexe sexe, Role role, String caractere, String alibi, String mobile) {
}
