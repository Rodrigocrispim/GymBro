package com.gymbro.backend.controllers;

import com.gymbro.backend.models.Localizacao;
import com.gymbro.backend.repositories.LocalizacaoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
@CrossOrigin(origins = "*")
public class LocalizacaoController {

    private final LocalizacaoRepository localizacaoRepository;

    public LocalizacaoController(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    @GetMapping
    public List<Localizacao> getTodas() {
        return localizacaoRepository.findAll();
    }
}

