package com.gymbro.backend.repositories;

import com.gymbro.backend.models.TipoTreino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTreinoRepository extends JpaRepository<TipoTreino, Integer> {
}
