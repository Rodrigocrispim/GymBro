package com.gymbro.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "periodo_dia")
public class PeriodoDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer periodo_id;

    private String periodo_nome;

    public PeriodoDia() {
    }
    // Getters e Setters
    public Integer getPeriodo_id() { return periodo_id; }
    public void setPeriodo_id(Integer periodo_id) { this.periodo_id = periodo_id; }
    public String getPeriodo_nome() { return periodo_nome; }
    public void setPeriodo_nome(String periodo_nome) { this.periodo_nome = periodo_nome; }
}
