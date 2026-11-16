package com.petcare.repository;

import com.petcare.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Optional<Consulta> findByUuid(UUID uuid);
}