package com.gymbro.backend.controllers;

import com.gymbro.backend.models.Utilizador;
import com.gymbro.backend.repositories.UtilizadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/utilizadores")
@CrossOrigin(origins = "*")
public class UtilizadorController {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorController(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    // Endpoint para o utilizador ver o seu próprio perfil
    @GetMapping("/me")
    public ResponseEntity<?> getMeuPerfil() {
        // 1. Extrair o email do utilizador logado através do Token JWT
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        // 2. Buscar os dados na Base de Dados
        Optional<Utilizador> user = utilizadorRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("Utilizador não encontrado");
        }

     
        return ResponseEntity.ok(user.get());
    }
    @PutMapping("/me")
public ResponseEntity<?> atualizarMeuPerfil(@RequestBody Utilizador dadosAtualizados) {
    // 1. Identificar o utilizador pelo Token
    String email = ((UserDetails) SecurityContextHolder.getContext()
                   .getAuthentication().getPrincipal()).getUsername();

    Utilizador utilizador = utilizadorRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

    // 2. Atualizar apenas os campos permitidos
    utilizador.setBiografia(dadosAtualizados.getBiografia());
   

    // 3. Salvar
    utilizadorRepository.save(utilizador);

    return ResponseEntity.ok("Perfil atualizado com sucesso!");
}
}
