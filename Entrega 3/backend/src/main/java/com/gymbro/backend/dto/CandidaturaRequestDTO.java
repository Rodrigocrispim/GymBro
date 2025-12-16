package com.gymbro.backend.dto;

public class CandidaturaRequestDTO {
    private Integer ofertaId;
    private String comentario; // "Olá, sou o João e treino há 2 anos..."

    // Getters e Setters
    public Integer getOfertaId() { return ofertaId; }
    public void setOfertaId(Integer ofertaId) { this.ofertaId = ofertaId; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
