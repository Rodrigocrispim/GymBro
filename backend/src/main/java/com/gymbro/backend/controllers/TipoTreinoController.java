package com.gymbro.backend.controllers;

import com.gymbro.backend.models.TipoTreino;
import com.gymbro.backend.repositories.TipoTreinoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-treino")
@CrossOrigin(origins = "*")
public class TipoTreinoController {

    private final TipoTreinoRepository tipoTreinoRepository;

    public TipoTreinoController(TipoTreinoRepository tipoTreinoRepository) {
        this.tipoTreinoRepository = tipoTreinoRepository;
    }

    @GetMapping
    public List<TipoTreino> getTodos() {
        return tipoTreinoRepository.findAll();
    }
}

