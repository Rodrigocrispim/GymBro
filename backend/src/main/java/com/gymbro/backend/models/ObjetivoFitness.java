package com.gymbro.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "objetivo_fitness") 
public class ObjetivoFitness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer objetivo_id;

    private String objetivo_nome;

    public ObjetivoFitness() {
    }
    // Getters e Setters
    public Integer getObjetivo_id() { return objetivo_id; }
    public void setObjetivo_id(Integer objetivo_id) { this.objetivo_id = objetivo_id; }
    public String getObjetivo_nome() { return objetivo_nome; }
    public void setObjetivo_nome(String objetivo_nome) { this.objetivo_nome = objetivo_nome; }
}
