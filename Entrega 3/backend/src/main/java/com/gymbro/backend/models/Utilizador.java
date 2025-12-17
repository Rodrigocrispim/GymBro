package com.gymbro.backend.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilizador")

public class Utilizador implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer utilizador_id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password_hash; // A password encriptada

    @Column(nullable = false)
    private String nome_completo;

 

    // --- CONSTRUTORES ---
    public Utilizador() {
    }

    public Utilizador(String email, String password_hash, String nome_completo) {
        this.email = email;
        this.password_hash = password_hash;
        this.nome_completo = nome_completo;
    }

   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Dizemos que todos os utilizadores têm o papel de "USER"
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password_hash; // O Spring vai ler a password daqui
    }

    @Override
    public String getUsername() {
        return email; // O Spring vai usar o EMAIL como "username" para login
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // A conta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // A conta nunca bloqueia
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // As credenciais nunca expiram
    }

    @Override
    public boolean isEnabled() {
        return true; // O utilizador está ativo
    }
    // Dentro de Utilizador.java
private String biografia; // Mudar de bio para biografia

// O nome do método TEM de ser getBiografia para o Java encontrar
public String getBiografia() {
    return biografia;
}

public void setBiografia(String biografia) {
    this.biografia = biografia;
}

    // --- GETTERS E SETTERS NORMAIS ---
    public Integer getUtilizador_id() { return utilizador_id; }
    public void setUtilizador_id(Integer utilizador_id) { this.utilizador_id = utilizador_id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNome_completo() { return nome_completo; }
    public void setNome_completo(String nome_completo) { this.nome_completo = nome_completo; }

    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }
   
}
