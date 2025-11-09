package com.gymbro.backend.controllers; // O novo package 'controllers'

// Importa as peças que vamos usar
import com.gymbro.backend.dto.OfertaDTO;
import com.gymbro.backend.repositories.OfertaRepository;

// Importa as anotações do Spring Web
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController: Diz ao Spring que esta classe é um "Controlador de API"
// e que os seus métodos vão devolver JSON.
@RestController

// @RequestMapping: Define o URL base para TODOS os endpoints nesta classe.
@RequestMapping("/api/ofertas")

// @CrossOrigin: A "Armadilha #1". Permite que a tua app Kotlin (que está
// num "IP" diferente) possa chamar este backend sem ser bloqueada.
@CrossOrigin(origins = "*") 
public class OfertaController {

    // --- Injeção de Dependência ---
    // Diz ao Spring: "Eu preciso de um 'OfertaRepository' para trabalhar."
    // O Spring vai automaticamente "injetar" (dar-te) a instância que ele criou.
    @Autowired
    private OfertaRepository ofertaRepository;


    // --- O TEU ENDPOINT (A Funcionalidade da Entrega) ---

    // @GetMapping: Diz ao Spring que este método responde a pedidos GET
    // no URL base (que definimos em cima como "/api/ofertas").
    @GetMapping
    public List<OfertaDTO> getTodasAsOfertas() {

        // 1. Chama a "Query Mágica" que criámos no Repository.
        // 2. O Repository vai à BD, faz os JOINs e enche os "sacos" (DTOs).
        // 3. O Spring Boot pega nesta List<OfertaDTO> e converte-a
        //    automaticamente em JSON para enviar como resposta.
        return ofertaRepository.findOfertasAbertasDTO();
    }

    // (No futuro, endpoints como @PostMapping("/criar") viriam aqui)
}