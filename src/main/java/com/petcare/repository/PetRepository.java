package com.petcare.repository;

import com.petcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "SELECT * FROM pet WHERE id_proprietario = :id", nativeQuery = true)
    List<Pet> buscarPetsPorProprietario(@Param("id") Long id);
}