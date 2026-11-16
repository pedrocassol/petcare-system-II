package com.petcare.repository;

import com.petcare.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "SELECT p.* FROM pet p " +
            "INNER JOIN proprietario pr ON p.id_proprietario = pr.id " +
            "WHERE pr.uuid = :uuid", nativeQuery = true)
    List<Pet> buscarPetsPorProprietario(@Param("uuid") UUID uuid);

    Optional<Pet> findByUuid(UUID uuid);
}