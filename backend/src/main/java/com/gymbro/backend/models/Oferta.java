package com.gymbro.backend.models;


import java.time.LocalDateTime;
import jakarta.persistence.*; 

@Entity
@Table(name = "oferta") // Liga à tabela 'oferta' (snake_case)
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer oferta_id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "MEDIUMTEXT")
    private String descricao;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime data_criacao;

    // Mapeamento do ENUM
    @Enumerated(EnumType.STRING) 
    @Column(name = "status_oferta", nullable = false)
    private StatusOferta status_oferta;


    // Relação 1: Muitas Ofertas para UM Utilizador (o Criador)
    @ManyToOne
    @JoinColumn(name = "criador_id", nullable = false)
    private Utilizador utilizador; 

    // Relação 2: Muitas Ofertas para UM TipoTreino
    @ManyToOne
    @JoinColumn(name = "tipo_treino_id", nullable = false)
    private TipoTreino tipoTreino;

    // Relação 3: Muitas Ofertas para UMA Localizacao
    @ManyToOne
    @JoinColumn(name = "localizacao_id", nullable = false)
    private Localizacao localizacao;

    // Relação 4: Muitas Ofertas para UM NivelTreino
    @ManyToOne
    @JoinColumn(name = "nivel_id", nullable = false)
    private NivelTreino nivelTreino;

    // Construtor vazio 
    public Oferta() {
    }

    // Getters e Setters 
    public Integer getOferta_id() { return oferta_id; }
    public void setOferta_id(Integer oferta_id) { this.oferta_id = oferta_id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getData_criacao() { return data_criacao; }
    public void setData_criacao(LocalDateTime data_criacao) { this.data_criacao = data_criacao; }
    public StatusOferta getStatus_oferta() { return status_oferta; }
    public void setStatus_oferta(StatusOferta status_oferta) { this.status_oferta = status_oferta; }
    public Utilizador getUtilizador() { return utilizador; }
    public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }
    public TipoTreino getTipoTreino() { return tipoTreino; }
    public void setTipoTreino(TipoTreino tipoTreino) { this.tipoTreino = tipoTreino; }
    public Localizacao getLocalizacao() { return localizacao; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }
    public NivelTreino getNivelTreino() { return nivelTreino; }
    public void setNivelTreino(NivelTreino nivelTreino) { this.nivelTreino = nivelTreino; }
}
