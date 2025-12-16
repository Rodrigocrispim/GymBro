package com.gymbro.backend.services;

import com.gymbro.backend.dto.OfertaRequestDTO;
import com.gymbro.backend.models.*;
import com.gymbro.backend.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OfertaService {

    private final OfertaRepository ofertaRepository;
    private final UtilizadorRepository utilizadorRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final NivelTreinoRepository nivelTreinoRepository;
    private final TipoTreinoRepository tipoTreinoRepository;
    private final DiaSemanaRepository diaSemanaRepository;
    private final PeriodoDiaRepository periodoDiaRepository;

    public OfertaService(OfertaRepository ofertaRepository, UtilizadorRepository utilizadorRepository,
                         LocalizacaoRepository localizacaoRepository, NivelTreinoRepository nivelTreinoRepository,
                         TipoTreinoRepository tipoTreinoRepository, DiaSemanaRepository diaSemanaRepository,
                         PeriodoDiaRepository periodoDiaRepository) {
        this.ofertaRepository = ofertaRepository;
        this.utilizadorRepository = utilizadorRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.nivelTreinoRepository = nivelTreinoRepository;
        this.tipoTreinoRepository = tipoTreinoRepository;
        this.diaSemanaRepository = diaSemanaRepository;
        this.periodoDiaRepository = periodoDiaRepository;
    }

    public void criarOferta(OfertaRequestDTO request) {
        // 1. Quem é o user?
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Utilizador criador = utilizadorRepository.findByEmail(email).orElseThrow();

        // 2. Buscar Entidades
        Localizacao local = localizacaoRepository.findById(request.getLocalizacaoId()).orElseThrow();
        NivelTreino nivel = nivelTreinoRepository.findById(request.getNivelId()).orElseThrow();
        TipoTreino tipo = tipoTreinoRepository.findById(request.getTipoTreinoId()).orElseThrow();
        
        // Buscar Dia e Periodo pelos IDs
        DiaSemana dia = diaSemanaRepository.findById(request.getDiaSemanaId()).orElseThrow();
        PeriodoDia periodo = periodoDiaRepository.findById(request.getPeriodoDiaId()).orElseThrow();

        // 3. Criar Oferta
        Oferta novaOferta = new Oferta();
        novaOferta.setTitulo(request.getTitulo());
        novaOferta.setDescricao(request.getDescricao());
        novaOferta.setUtilizador(criador);
        novaOferta.setLocalizacao(local);
        novaOferta.setNivelTreino(nivel);
        novaOferta.setTipoTreino(tipo);
        novaOferta.setDiaSemana(dia);    // <--- Simples!
        novaOferta.setPeriodoDia(periodo); // <--- Simples!
        novaOferta.setStatus_oferta(StatusOferta.ABERTA);
        novaOferta.setData_criacao(LocalDateTime.now());

        ofertaRepository.save(novaOferta);
    }
}
