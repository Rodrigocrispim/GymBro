package com.gymbro.backend.models;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "oferta_disponibilidade")
public class OfertaDisponibilidade {

    @EmbeddedId
    private OfertaDisponibilidadeId id;

    // --- RELAÇÕES ---

    @ManyToOne
    @MapsId("ofertaId") // Liga ao 'ofertaId' da nossa classe-chave
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;

    @ManyToOne
    @MapsId("diaId") // Liga ao 'diaId'
    @JoinColumn(name = "dia_id")
    private DiaSemana diaSemana;

    @ManyToOne
    @MapsId("periodoId") // Liga ao 'periodoId'
    @JoinColumn(name = "periodo_id")
    private PeriodoDia periodoDia;

    // Construtor vazio
    public OfertaDisponibilidade() {
    }

    // Getters e Setters
    public OfertaDisponibilidadeId getId() { return id; }
    public void setId(OfertaDisponibilidadeId id) { this.id = id; }
    public Oferta getOferta() { return oferta; }
    public void setOferta(Oferta oferta) { this.oferta = oferta; }
    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }
    public PeriodoDia getPeriodoDia() { return periodoDia; }
    public void setPeriodoDia(PeriodoDia periodoDia) { this.periodoDia = periodoDia; }
}
