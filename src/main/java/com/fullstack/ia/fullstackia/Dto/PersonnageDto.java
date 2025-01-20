package com.fullstack.ia.fullstackia.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fullstack.ia.fullstackia.Enum.Sexe;
import com.fullstack.ia.fullstackia.Enum.Role;

//@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonnageDto(Long id, String nom, String prenom, int age, Sexe sexe, Role role, String caractere, String alibi, String mobile) {

}
