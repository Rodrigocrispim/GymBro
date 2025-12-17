package com.gymbro.backend.dto;

// Isto NÃO é um @Entity. É uma classe Java "burra" (POJO).
// A sua única função é SERVIR DE "SACO" para a nossa resposta da API.
// Os campos aqui são os que o teu frontend (Kotlin) vai receber.
public class OfertaDTO {

    // Detalhes da Oferta
    private Integer ofertaId;
    private String titulo;
    private String descricao;

    // Detalhes "Juntados" (dos JOINs)
    private String nomeCriador;     // Da tabela 'utilizador'
    private String localizacaoNome; // Da tabela 'localizacao'
    private String nivelNome;       // Da tabela 'nivel_treino'
    private String tipoTreinoNome;  // Da tabela 'tipo_treino'
    private Integer criadorId;


public OfertaDTO(Integer ofertaId, String titulo, String descricao, String nomeCriador, 
    String localizacaoNome, String nivelNome, String tipoTreinoNome, Integer criadorId) {
this.ofertaId = ofertaId;
this.titulo = titulo;
this.descricao = descricao;
this.nomeCriador = nomeCriador;
this.localizacaoNome = localizacaoNome;
this.nivelNome = nivelNome;
this.tipoTreinoNome = tipoTreinoNome;
this.criadorId = criadorId;
}

// Getters
public Integer getOfertaId() { return ofertaId; }
public String getTitulo() { return titulo; }
public String getDescricao() { return descricao; }
public String getNomeCriador() { return nomeCriador; }
public String getLocalizacaoNome() { return localizacaoNome; }
public String getNivelNome() { return nivelNome; }
public String getTipoTreinoNome() { return tipoTreinoNome; }
public Integer getCriadorId() { return criadorId; }
}