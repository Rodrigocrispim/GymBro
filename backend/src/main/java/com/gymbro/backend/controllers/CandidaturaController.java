package com.gymbro.backend.controllers;

import com.gymbro.backend.dto.CandidaturaRequestDTO;
import com.gymbro.backend.services.CandidaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gymbro.backend.dto.AtualizarStatusDTO; 

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin(origins = "*")
public class CandidaturaController {

    private final CandidaturaService service;

    public CandidaturaController(CandidaturaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> candidatar(@RequestBody CandidaturaRequestDTO request) {
        try {
            service.criarCandidatura(request);
            return ResponseEntity.ok("Candidatura enviada com sucesso!");
        } catch (RuntimeException e) {
            // Se violar as regras (narcisista/desesperado), devolvemos erro 400 (Bad Request)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ... (código anterior)

    @PutMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatus(@PathVariable Integer id, @RequestBody AtualizarStatusDTO request) {
        try {
            service.atualizarStatus(id, request.getStatus());
            return ResponseEntity.ok("Status atualizado para: " + request.getStatus());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
