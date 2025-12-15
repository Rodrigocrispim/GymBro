package com.gymbro.backend.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class UtilizadorDisponibilidadeId implements Serializable {

    @Column(name = "utilizador_id")
    private Integer utilizadorId;

    @Column(name = "dia_id")
    private Integer diaId;

    @Column(name = "periodo_id")
    private Integer periodoId;

    
    public UtilizadorDisponibilidadeId() {
    }


    public Integer getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(Integer utilizadorId) { this.utilizadorId = utilizadorId; }
    public Integer getDiaId() { return diaId; }
    public void setDiaId(Integer diaId) { this.diaId = diaId; }
    public Integer getPeriodoId() { return periodoId; }
    public void setPeriodoId(Integer periodoId) { this.periodoId = periodoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilizadorDisponibilidadeId that = (UtilizadorDisponibilidadeId) o;
        return Objects.equals(utilizadorId, that.utilizadorId) &&
               Objects.equals(diaId, that.diaId) &&
               Objects.equals(periodoId, that.periodoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilizadorId, diaId, periodoId);
    }
}
