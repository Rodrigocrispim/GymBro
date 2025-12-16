package com.gymbro.backend.controllers;

import com.gymbro.backend.models.DiaSemana;
import com.gymbro.backend.repositories.DiaSemanaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dias-semana")
@CrossOrigin(origins = "*")
public class DiaSemanaController {

    private final DiaSemanaRepository diaSemanaRepository;

    public DiaSemanaController(DiaSemanaRepository diaSemanaRepository) {
        this.diaSemanaRepository = diaSemanaRepository;
    }

    @GetMapping
    public List<DiaSemana> getTodos() {
        return diaSemanaRepository.findAll();
    }
}

