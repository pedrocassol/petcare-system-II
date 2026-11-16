package com.petcare.service;

import com.petcare.model.Pet;
import com.petcare.repository.PetRepository;
import org.springframework.stereotype.Service;
import com.petcare.infra.PetNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

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

    public List<Pet> buscarPetsPorProprietario(UUID uuid) {
        return petRepository.buscarPetsPorProprietario(uuid);
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));
    }

    @Transactional
    public Pet atualizar(Long id, Pet pet) {
        Pet existente = petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));

        existente.setNome(pet.getNome());
        existente.setEspecie(pet.getEspecie());
        existente.setRaca(pet.getRaca());
        existente.setIdade(pet.getIdade());
        existente.setSexo(pet.getSexo());
        existente.setObservacoes(pet.getObservacoes());
        existente.setProprietario(pet.getProprietario());

        return existente;
    }

    public void deletar(Long id) {
        petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));

        petRepository.deleteById(id);
    }

    public Pet buscarPorUuid(UUID uuid) {
        return petRepository.findByUuid(uuid)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o UUID: " + uuid
                ));
    }

    @Transactional
    public Pet atualizarPorUuid(UUID uuid, Pet pet) {
        Pet existente = buscarPorUuid(uuid);

        existente.setNome(pet.getNome());
        existente.setEspecie(pet.getEspecie());
        existente.setRaca(pet.getRaca());
        existente.setIdade(pet.getIdade());
        existente.setSexo(pet.getSexo());
        existente.setObservacoes(pet.getObservacoes());
        existente.setProprietario(pet.getProprietario());

        return existente;
    }

    public void deletarPorUuid(UUID uuid) {
        Pet existente = buscarPorUuid(uuid);
        petRepository.deleteById(existente.getId());
    }
}