package com.gymbro.backend.controllers;

import com.gymbro.backend.models.NivelTreino;
import com.gymbro.backend.repositories.NivelTreinoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/niveis-treino")
@CrossOrigin(origins = "*")
public class NivelTreinoController {

    private final NivelTreinoRepository nivelTreinoRepository;

    public NivelTreinoController(NivelTreinoRepository nivelTreinoRepository) {
        this.nivelTreinoRepository = nivelTreinoRepository;
    }

    @GetMapping
    public List<NivelTreino> getTodos() {
        return nivelTreinoRepository.findAll();
    }
}

