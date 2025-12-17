package com.gymbro.backend.services;

import com.gymbro.backend.dto.CandidaturaRequestDTO;
import com.gymbro.backend.models.*;
import com.gymbro.backend.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final OfertaRepository ofertaRepository;
    private final UtilizadorRepository utilizadorRepository;

    public CandidaturaService(CandidaturaRepository candidaturaRepository, 
                              OfertaRepository ofertaRepository, 
                              UtilizadorRepository utilizadorRepository) {
        this.candidaturaRepository = candidaturaRepository;
        this.ofertaRepository = ofertaRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public void criarCandidatura(CandidaturaRequestDTO request) {
        // 1. Quem é o candidato? (Lendo o Token)
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Utilizador candidato = utilizadorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        // 2. Qual é a oferta?
        Oferta oferta = ofertaRepository.findById(request.getOfertaId())
                .orElseThrow(() -> new RuntimeException("Oferta não encontrada"));

        // --- REGRAS DE SEGURANÇA  ---

        // Comparamos os IDs (atenção ao equals para Integer)
        if (oferta.getUtilizador().getUtilizador_id().equals(candidato.getUtilizador_id())) {
            throw new RuntimeException("Não podes candidatar-te à tua própria oferta!");
        }

        // Regra 2: O Desesperado (Não pode candidatar-se 2 vezes)
        if (candidaturaRepository.existsByCandidatoAndOferta(candidato, oferta)) {
            throw new RuntimeException("Já te candidataste a esta oferta!");
        }

        // 3. Tudo OK? Criar a candidatura
        Candidatura nova = new Candidatura();
        nova.setCandidato(candidato);
        nova.setOferta(oferta);
        nova.setComentario_inicial(request.getComentario());
        nova.setStatus("PENDENTE");
        nova.setData_envio(LocalDateTime.now());

        candidaturaRepository.save(nova);
    }
    // ... (imports e código anterior)

    public void atualizarStatus(Integer candidaturaId, String novoStatus) {
        // 1. Validar o input (só aceitamos "ACEITE" ou "REJEITADA")
        if (!novoStatus.equals("ACEITE") && !novoStatus.equals("REJEITADA")) {
            throw new RuntimeException("Status inválido. Usa ACEITE ou REJEITADA.");
        }

        // 2. Buscar a candidatura
        Candidatura candidatura = candidaturaRepository.findById(candidaturaId)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));

        // 3. SEGURANÇA: Quem está a tentar fazer isto?
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Utilizador dono = utilizadorRepository.findByEmail(email).orElseThrow();

        // 4. Verificar se o user logado é o CRIADOR da oferta
        if (!candidatura.getOferta().getUtilizador().getUtilizador_id().equals(dono.getUtilizador_id())) {
            throw new RuntimeException("Não tens permissão para gerir esta candidatura!");
        }

        // 5. Atualizar e Gravar
        candidatura.setStatus(novoStatus);
        candidaturaRepository.save(candidatura);
    }
}
