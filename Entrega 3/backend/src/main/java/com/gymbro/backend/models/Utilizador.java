package com.gymbro.backend.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "utilizador")
// AQUI ESTÁ A MUDANÇA: "implements UserDetails"
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

    // ... (outros campos como biografia, localizacao, etc. podes manter ou adicionar depois)
    // Para simplificar a autenticação agora, vamos focar nestes 3.

    // --- CONSTRUTORES ---
    public Utilizador() {
    }

    public Utilizador(String email, String password_hash, String nome_completo) {
        this.email = email;
        this.password_hash = password_hash;
        this.nome_completo = nome_completo;
    }

    // --- MÉTODOS OBRIGATÓRIOS DO SPRING SECURITY (UserDetails) ---

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

    // --- GETTERS E SETTERS NORMAIS ---
    public Integer getUtilizador_id() { return utilizador_id; }
    public void setUtilizador_id(Integer utilizador_id) { this.utilizador_id = utilizador_id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNome_completo() { return nome_completo; }
    public void setNome_completo(String nome_completo) { this.nome_completo = nome_completo; }

    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }
    // Nota: O getPassword_hash não é estritamente necessário se já tens o getPassword(), 
    // mas podes manter.
}
