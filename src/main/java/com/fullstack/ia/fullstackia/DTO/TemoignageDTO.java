package com.fullstack.ia.fullstackia.DTO;

public record TemoignageDTO(long id, String description, long temoinId, long suspectId) {
    public Long getTemoinId() {
        return temoinId;
    }

    public Long getSuspectId() {
        return suspectId;
    }
}