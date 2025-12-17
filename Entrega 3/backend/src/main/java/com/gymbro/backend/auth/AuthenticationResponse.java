package com.gymbro.backend.auth;

public class AuthenticationResponse {
    private String token;
    private Integer id; // Adiciona o campo ID

    
    public AuthenticationResponse(String token, Integer id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Integer getId() { return id; } // Adiciona o getter
    public void setId(Integer id) { this.id = id; } // Adiciona o setter
}
