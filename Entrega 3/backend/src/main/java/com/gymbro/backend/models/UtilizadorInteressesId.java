package com.gymbro.backend.models;


import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;


@Embeddable
public class UtilizadorInteressesId implements Serializable {


    @Column(name = "utilizador_id")
    private Integer utilizadorId;

    @Column(name = "tipo_treino_id")
    private Integer tipoTreinoId;

    // Construtor vazio
    public UtilizadorInteressesId() {
    }

    

    public Integer getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(Integer utilizadorId) { this.utilizadorId = utilizadorId; }
    public Integer getTipoTreinoId() { return tipoTreinoId; }
    public void setTipoTreinoId(Integer tipoTreinoId) { this.tipoTreinoId = tipoTreinoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilizadorInteressesId that = (UtilizadorInteressesId) o;
        return Objects.equals(utilizadorId, that.utilizadorId) &&
               Objects.equals(tipoTreinoId, that.tipoTreinoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilizadorId, tipoTreinoId);
    }
}
