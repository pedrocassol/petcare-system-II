
package com.petcare.service;

import com.petcare.dto.PetPatchDTO;
import com.petcare.infra.PetNotFoundException;
import com.petcare.infra.ProprietarioNotFoundException;
import com.petcare.model.Pet;
import com.petcare.model.Proprietario;
import com.petcare.repository.PetRepository;
import com.petcare.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ProprietarioRepository proprietarioRepository;

    public PetService(
            PetRepository petRepository,
            ProprietarioRepository proprietarioRepository) {

        this.petRepository = petRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    @Transactional
    public Pet salvar(Pet pet) {
        Proprietario proprietario =
                buscarProprietarioPorId(pet.getProprietario().getId());

        pet.setProprietario(proprietario);

        return petRepository.save(pet);
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public List<Pet> buscarPetsPorProprietario(UUID uuid) {
        return petRepository.buscarPetsPorProprietario(uuid);
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

        Proprietario proprietario =
                buscarProprietarioPorId(pet.getProprietario().getId());

        existente.setNome(pet.getNome());
        existente.setEspecie(pet.getEspecie());
        existente.setRaca(pet.getRaca());
        existente.setIdade(pet.getIdade());
        existente.setSexo(pet.getSexo());
        existente.setObservacoes(pet.getObservacoes());
        existente.setProprietario(proprietario);

        return existente;
    }

    public void deletarPorUuid(UUID uuid) {
        Pet existente = buscarPorUuid(uuid);
        petRepository.deleteById(existente.getId());
    }

    @Transactional
    public Pet atualizarParcialmente(UUID uuid, PetPatchDTO dados) {

        Pet existente = buscarPorUuid(uuid);

        if (dados.getNome() != null) {
            existente.setNome(dados.getNome());
        }

        if (dados.getEspecie() != null) {
            existente.setEspecie(dados.getEspecie());
        }

        if (dados.getRaca() != null) {
            existente.setRaca(dados.getRaca());
        }

        if (dados.getIdade() != null) {
            existente.setIdade(dados.getIdade());
        }

        if (dados.getSexo() != null) {
            existente.setSexo(dados.getSexo());
        }

        if (dados.getObservacoes() != null) {
            existente.setObservacoes(dados.getObservacoes());
        }

        return existente;
    }

    private Proprietario buscarProprietarioPorId(Long id) {
        return proprietarioRepository.findById(id)
                .orElseThrow(() -> new ProprietarioNotFoundException(
                        "Proprietário não encontrado para o ID: " + id
                ));
    }
}
