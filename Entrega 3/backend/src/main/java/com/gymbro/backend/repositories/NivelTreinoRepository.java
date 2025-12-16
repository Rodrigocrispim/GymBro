package com.gymbro.backend.repositories;

import com.gymbro.backend.models.NivelTreino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelTreinoRepository extends JpaRepository<NivelTreino, Integer> {
}
