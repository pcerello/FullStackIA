package com.fullstack.ia.fullstackia.Dto;

import com.fullstack.ia.fullstackia.Entity.PersonnagesEntity;

public record TemoignageDto(String incriminant, String disculpant, String type, Boolean isTrue, PersonnagesEntity temoin, PersonnagesEntity suspect) {
}