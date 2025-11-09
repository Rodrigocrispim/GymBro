package com.gymbro.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_treino") 
public class TipoTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipo_treino_id;

    private String nome;

    public TipoTreino() {
    }
    // Getters e Setters
    public Integer getTipo_treino_id() { return tipo_treino_id; }
    public void setTipo_treino_id(Integer tipo_treino_id) { this.tipo_treino_id = tipo_treino_id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
