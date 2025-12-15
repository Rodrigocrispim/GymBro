package com.gymbro.backend.repositories;

import com.gymbro.backend.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {

    // A "Query Mágica" para encontrar alguém pelo email
    // O Optional significa: "Pode devolver um utilizador, ou pode devolver vazio (se não existir)"
    Optional<Utilizador> findByEmail(String email);

}
