package com.gymbro.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nivel_treino") 
public class NivelTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nivel_id;

    private String nivel_nome;

    public NivelTreino() {
    }
    // Getters e Setters
    public Integer getNivel_id() { return nivel_id; }
    public void setNivel_id(Integer nivel_id) { this.nivel_id = nivel_id; }
    public String getNivel_nome() { return nivel_nome; }
    public void setNivel_nome(String nivel_nome) { this.nivel_nome = nivel_nome; }
}
