package com.gymbro.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer candidatura_id;

    @ManyToOne
    @JoinColumn(name = "oferta_id", nullable = false)
    private Oferta oferta;

    @ManyToOne
    @JoinColumn(name = "candidato_id", nullable = false)
    private Utilizador candidato;

    // Atenção: Vamos usar String para simplificar o ENUM no Java
    // Na BD continua a ser ENUM, mas o Java trata como texto "PENDENTE", "ACEITE"...
    @Column(nullable = false, columnDefinition = "ENUM('PENDENTE', 'ACEITE', 'REJEITADA')")
    private String status = "PENDENTE";

    private String comentario_inicial;
    
    private LocalDateTime data_envio;

    // --- Getters e Setters ---
    public Integer getCandidatura_id() { return candidatura_id; }
    public void setCandidatura_id(Integer id) { this.candidatura_id = id; }

    public Oferta getOferta() { return oferta; }
    public void setOferta(Oferta oferta) { this.oferta = oferta; }

    public Utilizador getCandidato() { return candidato; }
    public void setCandidato(Utilizador candidato) { this.candidato = candidato; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComentario_inicial() { return comentario_inicial; }
    public void setComentario_inicial(String comentario) { this.comentario_inicial = comentario; }

    public LocalDateTime getData_envio() { return data_envio; }
    public void setData_envio(LocalDateTime data) { this.data_envio = data; }
}
