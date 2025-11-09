package com.gymbro.backend.models;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class OfertaDisponibilidadeId implements Serializable {

    @Column(name = "oferta_id")
    private Integer ofertaId;

    @Column(name = "dia_id")
    private Integer diaId;

    @Column(name = "periodo_id")
    private Integer periodoId;

    // Construtor vazio
    public OfertaDisponibilidadeId() {
    }

    // Getters, Setters, hashCode e equals
    public Integer getOfertaId() { return ofertaId; }
    public void setOfertaId(Integer ofertaId) { this.ofertaId = ofertaId; }
    public Integer getDiaId() { return diaId; }
    public void setDiaId(Integer diaId) { this.diaId = diaId; }
    public Integer getPeriodoId() { return periodoId; }
    public void setPeriodoId(Integer periodoId) { this.periodoId = periodoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfertaDisponibilidadeId that = (OfertaDisponibilidadeId) o;
        return Objects.equals(ofertaId, that.ofertaId) &&
               Objects.equals(diaId, that.diaId) &&
               Objects.equals(periodoId, that.periodoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ofertaId, diaId, periodoId);
    }
}
