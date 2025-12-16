package com.gymbro.backend.models;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilizador_disponibilidade")
public class UtilizadorDisponibilidade {

    @EmbeddedId
    private UtilizadorDisponibilidadeId id;

    // RELAÇÕES 

    @ManyToOne
    @MapsId("utilizadorId") 
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @ManyToOne
    @MapsId("diaId") 
    @JoinColumn(name = "dia_id")
    private DiaSemana diaSemana;

    @ManyToOne
    @MapsId("periodoId") 
    @JoinColumn(name = "periodo_id")
    private PeriodoDia periodoDia;

    // Construtor vazio
    public UtilizadorDisponibilidade() {
    }

    // Getters e Setters
    public UtilizadorDisponibilidadeId getId() { return id; }
    public void setId(UtilizadorDisponibilidadeId id) { this.id = id; }
    public Utilizador getUtilizador() { return utilizador; }
    public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }
    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }
    public PeriodoDia getPeriodoDia() { return periodoDia; }
    public void setPeriodoDia(PeriodoDia periodoDia) { this.periodoDia = periodoDia; }
}