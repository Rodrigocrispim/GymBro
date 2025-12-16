package com.gymbro.backend.repositories;

import com.gymbro.backend.models.PeriodoDia; // <--- Nome correto
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoDiaRepository extends JpaRepository<PeriodoDia, Integer> {
}
