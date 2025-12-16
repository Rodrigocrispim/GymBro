package com.gymbro.backend.dto;

public class CandidaturaResponseDTO {
    private Integer id; // ID da Candidatura (para depois aceitares/rejeitares)
    private String nomeCandidato;
    private String comentario;
    private String status; // "PENDENTE", "ACEITE", "REJEITADA"
    private String tituloOferta; // <--- NOVO: Para o criador saber qual é o treino

    public CandidaturaResponseDTO(Integer id, String nomeCandidato, String comentario, String status, String tituloOferta) {
        this.id = id;
        this.nomeCandidato = nomeCandidato;
        this.comentario = comentario;
        this.status = status;
        this.tituloOferta = tituloOferta;
    }

    // Getters
    public Integer getId() { return id; }
    public String getNomeCandidato() { return nomeCandidato; }
    public String getComentario() { return comentario; }
    public String getStatus() { return status; }
    public String getTituloOferta() { return tituloOferta; }
}