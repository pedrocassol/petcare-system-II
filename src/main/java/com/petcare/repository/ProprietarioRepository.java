package com.petcare.repository;

import com.petcare.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
}