package com.gymbro.backend.dto;

public class OfertaRequestDTO {
    private String titulo;
    private String descricao;
    private Integer localizacaoId;
    private Integer nivelId;
    private Integer tipoTreinoId;
    
    // IDs simples para o dia e período
    private Integer diaSemanaId; 
    private Integer periodoDiaId;

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Integer localizacaoId) { this.localizacaoId = localizacaoId; }

    public Integer getNivelId() { return nivelId; }
    public void setNivelId(Integer nivelId) { this.nivelId = nivelId; }

    public Integer getTipoTreinoId() { return tipoTreinoId; }
    public void setTipoTreinoId(Integer tipoTreinoId) { this.tipoTreinoId = tipoTreinoId; }

    public Integer getDiaSemanaId() { return diaSemanaId; }
    public void setDiaSemanaId(Integer diaSemanaId) { this.diaSemanaId = diaSemanaId; }

    public Integer getPeriodoDiaId() { return periodoDiaId; }
    public void setPeriodoDiaId(Integer periodoDiaId) { this.periodoDiaId = periodoDiaId; }
    // Em OfertaRequestDTO.java
private Integer userId; 

public Integer getUserId() { return userId; }
public void setUserId(Integer userId) { this.userId = userId; }

}