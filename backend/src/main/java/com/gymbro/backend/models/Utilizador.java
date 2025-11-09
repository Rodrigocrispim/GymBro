package com.gymbro.backend.models;


import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "utilizador") 
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer utilizador_id;

    // --- Colunas de Autenticação ---
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    // --- Colunas de Perfil ---
    @Column(name = "nome_completo", nullable = false)
    private String nome_completo;

    @Column(name = "biografia", columnDefinition = "TEXT")
    private String biografia;

    @Column(name = "url_foto_perfil")
    private String url_foto_perfil;


    //  RELAÇÕES 

    // Relação 1: Muitos Utilizadores para UM NivelTreino
    @ManyToOne 
    @JoinColumn(name = "nivel_id") 
    private NivelTreino nivelTreino; 

    // Relação 2: Muitos Utilizadores para UMA Localizacao
    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    // Relação 3: Muitos Utilizadores para UM ObjetivoFitness
    @ManyToOne
    @JoinColumn(name = "objetivo_id")
    private ObjetivoFitness objetivoFitness;
    
    // --- Construtor, Getters e Setters ---
    
    // Construtor vazio 
    public Utilizador() {
    }

    // Getters (para ler)
    public Integer getUtilizador_id() { return utilizador_id; }
    public String getEmail() { return email; }
    public String getPassword_hash() { return password_hash; }
    public String getNome_completo() { return nome_completo; }
    public String getBiografia() { return biografia; }
    public String getUrl_foto_perfil() { return url_foto_perfil; }
    public NivelTreino getNivelTreino() { return nivelTreino; }
    public Localizacao getLocalizacao() { return localizacao; }
    public ObjetivoFitness getObjetivoFitness() { return objetivoFitness; }

    // Setters 
    public void setUtilizador_id(Integer utilizador_id) { this.utilizador_id = utilizador_id; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }
    public void setNome_completo(String nome_completo) { this.nome_completo = nome_completo; }
    public void setBiografia(String biografia) { this.biografia = biografia; }
    public void setUrl_foto_perfil(String url_foto_perfil) { this.url_foto_perfil = url_foto_perfil; }
    public void setNivelTreino(NivelTreino nivelTreino) { this.nivelTreino = nivelTreino; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }
    public void setObjetivoFitness(ObjetivoFitness objetivoFitness) { this.objetivoFitness = objetivoFitness; }
}
