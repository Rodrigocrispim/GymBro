package com.gymbro.backend.models;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilizador_interesses")
public class UtilizadorInteresses {

    
    @EmbeddedId
    private UtilizadorInteressesId id;

    // --- RELAÇÕES ---
    

    // 1. Ligação ao Utilizador
    @ManyToOne
    @MapsId("utilizadorId") // Mapeia o 'utilizadorId' da nossa classe-chave...
    @JoinColumn(name = "utilizador_id") // ...para a coluna FK 'utilizador_id'
    private Utilizador utilizador;

    // 2. Ligação ao TipoTreino
    @ManyToOne
    @MapsId("tipoTreinoId") // Mapeia o 'tipoTreinoId' da nossa classe-chave...
    @JoinColumn(name = "tipo_treino_id") // ...para a coluna FK 'tipo_treino_id'
    private TipoTreino tipoTreino;

    // Construtor vazio
    public UtilizadorInteresses() {
    }

    // Getters e Setters
    public UtilizadorInteressesId getId() { return id; }
    public void setId(UtilizadorInteressesId id) { this.id = id; }
    public Utilizador getUtilizador() { return utilizador; }
    public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }
    public TipoTreino getTipoTreino() { return tipoTreino; }
    public void setTipoTreino(TipoTreino tipoTreino) { this.tipoTreino = tipoTreino; }
}
