package com.gymbro.backend.auth;

import com.gymbro.backend.config.JwtService;
import com.gymbro.backend.models.Utilizador;
import com.gymbro.backend.repositories.UtilizadorRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UtilizadorRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Construtor Manual
    public AuthenticationService(UtilizadorRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // --- LÓGICA DE REGISTO ---
    public AuthenticationResponse register(RegisterRequest request) {
        // 1. Criar o Utilizador com os dados do formulário
        var user = new Utilizador();
        user.setNome_completo(request.getNomeCompleto());
        user.setEmail(request.getEmail());
        
        // 2. IMPORTANTE: Encriptar a password antes de salvar!
        user.setPassword_hash(passwordEncoder.encode(request.getPassword()));

        // 3. Salvar na Base de Dados
        repository.save(user);

        // 4. Gerar o Token automaticamente para ele entrar logo
        var jwtToken = jwtService.generateToken(user.getUsername());
        
        return new AuthenticationResponse(jwtToken);
    }

    // --- LÓGICA DE LOGIN ---
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // 1. O "AuthenticationManager" verifica se a pass está certa
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Se não deu erro acima, vamos buscar o utilizador à BD
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(); // (Aqui podias tratar o erro melhor, mas para já serve)

        // 3. Geramos um novo token
        var jwtToken = jwtService.generateToken(user.getUsername());

        return new AuthenticationResponse(jwtToken);
    }
}
