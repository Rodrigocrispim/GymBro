package com.gymbro.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "oferta")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oferta_id;

    private String titulo;
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private StatusOferta status_oferta;
    
    private LocalDateTime data_criacao;

    @ManyToOne
    @JoinColumn(name = "criador_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @ManyToOne
    @JoinColumn(name = "nivel_id")
    private NivelTreino nivelTreino;

    @ManyToOne
    @JoinColumn(name = "tipo_treino_id")
    private TipoTreino tipoTreino;

    // --- AS NOVIDADES ---
    @ManyToOne
    @JoinColumn(name = "dia_id") // Nome exato da coluna no SQL novo
    private DiaSemana diaSemana;

    @ManyToOne
    @JoinColumn(name = "periodo_id") // Nome exato da coluna no SQL novo
    private PeriodoDia periodoDia;

    // --- GETTERS E SETTERS ---
    public Integer getOferta_id() { return oferta_id; }
    public void setOferta_id(Integer oferta_id) { this.oferta_id = oferta_id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public StatusOferta getStatus_oferta() { return status_oferta; }
    public void setStatus_oferta(StatusOferta status_oferta) { this.status_oferta = status_oferta; }

    public LocalDateTime getData_criacao() { return data_criacao; }
    public void setData_criacao(LocalDateTime data_criacao) { this.data_criacao = data_criacao; }

    public Utilizador getUtilizador() { return utilizador; }
    public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }

    public Localizacao getLocalizacao() { return localizacao; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public NivelTreino getNivelTreino() { return nivelTreino; }
    public void setNivelTreino(NivelTreino nivelTreino) { this.nivelTreino = nivelTreino; }

    public TipoTreino getTipoTreino() { return tipoTreino; }
    public void setTipoTreino(TipoTreino tipoTreino) { this.tipoTreino = tipoTreino; }

    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }

    public PeriodoDia getPeriodoDia() { return periodoDia; }
    public void setPeriodoDia(PeriodoDia periodoDia) { this.periodoDia = periodoDia; }
}
