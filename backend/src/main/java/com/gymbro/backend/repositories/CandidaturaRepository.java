package com.gymbro.backend.repositories;

import com.gymbro.backend.models.Candidatura;
import com.gymbro.backend.models.Oferta;
import com.gymbro.backend.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // <--- Não te esqueças de importar List

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Integer> {
    
    boolean existsByCandidatoAndOferta(Utilizador candidato, Oferta oferta);

    // NOVO: Buscar lista por Oferta
    List<Candidatura> findByOferta(Oferta oferta);
}