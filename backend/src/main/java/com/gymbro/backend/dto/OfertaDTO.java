package com.gymbro.backend.dto;

// Isto NÃO é um @Entity. É uma classe Java "burra" (POJO).
// A sua única função é SERVIR DE "SACO" para a nossa resposta da API.
// Os campos aqui são os que o teu frontend (Kotlin) vai receber.
public class OfertaDTO {

    // Detalhes da Oferta
    private Integer ofertaId;
    private String titulo;
    private String descricao;

    // Detalhes "Juntados" (dos JOINs)
    private String nomeCriador;     // Da tabela 'utilizador'
    private String localizacaoNome; // Da tabela 'localizacao'
    private String nivelNome;       // Da tabela 'nivel_treino'
    private String tipoTreinoNome;  // Da tabela 'tipo_treino'

    // NOTA: Para esta primeira entrega, NÃO vamos incluir a disponibilidade
    // (ex: "Segunda de Manhã"), porque isso exige uma query MUITO mais
    // complexa (GROUP BY / CONCAT). Vamos focar-nos no principal.


    // --- CONSTRUTOR ---
    // Este é o "molde" que o nosso Repository vai usar para encher o saco.
    // A ordem dos parâmetros aqui TEM DE SER 100% IGUAL à ordem 
    // da nossa query no Passo 5.
    public OfertaDTO(Integer ofertaId, String titulo, String descricao, 
                     String nomeCriador, String localizacaoNome, 
                     String nivelNome, String tipoTreinoNome) {
        this.ofertaId = ofertaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nomeCriador = nomeCriador;
        this.localizacaoNome = localizacaoNome;
        this.nivelNome = nivelNome;
        this.tipoTreinoNome = tipoTreinoNome;
    }

    // --- GETTERS ---
    // O frontend precisa disto para ler os valores (o Spring usa-os para criar o JSON)
    public Integer getOfertaId() { return ofertaId; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getNomeCriador() { return nomeCriador; }
    public String getLocalizacaoNome() { return localizacaoNome; }
    public String getNivelNome() { return nivelNome; }
    public String getTipoTreinoNome() { return tipoTreinoNome; }
}
