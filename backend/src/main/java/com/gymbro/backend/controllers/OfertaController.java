package com.gymbro.backend.controllers;

import com.gymbro.backend.dto.CandidaturaResponseDTO;
import com.gymbro.backend.dto.OfertaDTO;
import com.gymbro.backend.dto.OfertaRequestDTO;
import com.gymbro.backend.models.Candidatura;
import com.gymbro.backend.models.Oferta;
import com.gymbro.backend.repositories.CandidaturaRepository;
import com.gymbro.backend.repositories.OfertaRepository;
import com.gymbro.backend.services.OfertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertaController {

    private final OfertaService ofertaService;
    private final OfertaRepository ofertaRepository;
    private final CandidaturaRepository candidaturaRepository; // <--- NOVO REPOSITÓRIO

    // Construtor atualizado com tudo o que precisamos
    public OfertaController(OfertaService ofertaService, 
                            OfertaRepository ofertaRepository,
                            CandidaturaRepository candidaturaRepository) {
        this.ofertaService = ofertaService;
        this.ofertaRepository = ofertaRepository;
        this.candidaturaRepository = candidaturaRepository;
    }

    // 1. VER TODAS AS OFERTAS (Feed Público)
    @GetMapping
    public ResponseEntity<List<OfertaDTO>> getAllOfertas() {
        List<OfertaDTO> lista = ofertaRepository.findAll().stream()
            .map(oferta -> new OfertaDTO(
                oferta.getOferta_id(),
                oferta.getTitulo(),
                oferta.getDescricao(),
                oferta.getUtilizador().getNome_completo(),
                oferta.getLocalizacao().getConcelho(),
                oferta.getNivelTreino().getNivel_nome(),
                oferta.getTipoTreino().getNome()
            ))
            .collect(Collectors.toList());
            
        return ResponseEntity.ok(lista);
    }

    // 2. CRIAR OFERTA
    @PostMapping
    public ResponseEntity<String> criarOferta(@RequestBody OfertaRequestDTO request) {
        ofertaService.criarOferta(request);
        return ResponseEntity.ok("Oferta criada com sucesso!");
    }

    // 3. VER CANDIDATURAS DE UMA OFERTA ESPECÍFICA (Para o Painel de Gestão)
    @GetMapping("/{ofertaId}/candidaturas")
    public ResponseEntity<List<CandidaturaResponseDTO>> getCandidaturas(@PathVariable Integer ofertaId) {
    
        Oferta oferta = ofertaRepository.findById(ofertaId)
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));
    
        List<Candidatura> candidaturas = candidaturaRepository.findByOferta(oferta);
    
        List<CandidaturaResponseDTO> resposta = candidaturas.stream()
                .map(c -> new CandidaturaResponseDTO(
                        c.getCandidatura_id(),
                        c.getCandidato().getNome_completo(),
                        c.getComentario_inicial(),
                        c.getStatus(),
                        oferta.getTitulo()
                ))
                .collect(Collectors.toList());
    
        return ResponseEntity.ok(resposta);
    }
}