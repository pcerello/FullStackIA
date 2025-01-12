package com.fullstack.ia.fullstackia.DTO;

public record PreuveDTO(long id, String type, String description, long temoignageId) {
    public Long getTemoignageId() {
        return temoignageId;
    }
}