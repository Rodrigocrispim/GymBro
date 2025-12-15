package com.gymbro.backend.controllers;

import com.gymbro.backend.models.PeriodoDia;
import com.gymbro.backend.repositories.PeriodoDiaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/periodos-dia")
@CrossOrigin(origins = "*")
public class PeriodoDiaController {

    private final PeriodoDiaRepository periodoDiaRepository;

    public PeriodoDiaController(PeriodoDiaRepository periodoDiaRepository) {
        this.periodoDiaRepository = periodoDiaRepository;
    }

    @GetMapping
    public List<PeriodoDia> getTodos() {
        return periodoDiaRepository.findAll();
    }
}

