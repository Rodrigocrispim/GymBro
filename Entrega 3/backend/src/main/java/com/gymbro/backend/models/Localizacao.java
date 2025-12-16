package com.gymbro.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "localizacao") 
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer localizacao_id;

    private String distrito;
    private String concelho;

    public Localizacao() {
    }
    // Getters e Setters
    public Integer getLocalizacao_id() { return localizacao_id; }
    public void setLocalizacao_id(Integer localizacao_id) { this.localizacao_id = localizacao_id; }
    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }
    public String getConcelho() { return concelho; }
    public void setConcelho(String concelho) { this.concelho = concelho; }
}