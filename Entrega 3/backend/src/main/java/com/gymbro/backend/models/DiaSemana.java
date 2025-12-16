package com.gymbro.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dia_semana") 
public class DiaSemana {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dia_id;

    private String nome_dia;

    public DiaSemana() {
    }
    // Getters e Setters
    public Integer getDia_id() { return dia_id; }
    public void setDia_id(Integer dia_id) { this.dia_id = dia_id; }
    public String getNome_dia() { return nome_dia; }
    public void setNome_dia(String nome_dia) { this.nome_dia = nome_dia; }
}
