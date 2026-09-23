package com.petcare.service;

import com.petcare.model.Pet;
import com.petcare.repository.PetRepository;
import org.springframework.stereotype.Service;
import com.petcare.infra.PetNotFoundException;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));
    }

    public Pet atualizar(Long id, Pet pet) {
        Pet existente = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));

        pet.setId(existente.getId());
        return petRepository.save(pet);
    }

    public void deletar(Long id) {
        petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));

        petRepository.deleteById(id);
    }
}