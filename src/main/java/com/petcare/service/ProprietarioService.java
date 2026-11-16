package com.petcare.service;

import com.petcare.model.Proprietario;
import com.petcare.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;
import com.petcare.infra.ProprietarioNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

import java.util.List;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public Proprietario salvar(Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    public List<Proprietario> listarTodos() {
        return proprietarioRepository.findAll();
    }

    public Proprietario buscarPorUuid(UUID uuid) {
        return proprietarioRepository.findByUuid(uuid)
                .orElseThrow(() -> new ProprietarioNotFoundException(
                        "Proprietário não encontrado para o UUID: " + uuid
                ));
    }

    @Transactional
    public Proprietario atualizarPorUuid(UUID uuid, Proprietario proprietario) {
        Proprietario existente = buscarPorUuid(uuid);

        existente.setNome(proprietario.getNome());
        existente.setEmail(proprietario.getEmail());
        existente.setTelefone(proprietario.getTelefone());
        existente.setEndereco(proprietario.getEndereco());

        return existente;
    }

    public void deletarPorUuid(UUID uuid) {
        Proprietario existente = buscarPorUuid(uuid);
        proprietarioRepository.deleteById(existente.getId());
    }
}