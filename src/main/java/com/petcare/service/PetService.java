package com.petcare.service;

import com.petcare.model.Pet;
import com.petcare.repository.PetRepository;
import org.springframework.stereotype.Service;

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
        return petRepository.findById(id).orElse(null);
    }

    public Pet atualizar(Long id, Pet pet) {
        pet.setId(id);
        return petRepository.save(pet);
    }

    public void deletar(Long id) {
        petRepository.deleteById(id);
    }
}