package com.petcare.service;

import com.petcare.model.Proprietario;
import com.petcare.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;

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

    public Proprietario buscarPorId(Long id) {
        return proprietarioRepository.findById(id).orElse(null);
    }

    public Proprietario atualizar(Long id, Proprietario proprietario) {
        proprietario.setId(id);
        return proprietarioRepository.save(proprietario);
    }

    public void deletar(Long id) {
        proprietarioRepository.deleteById(id);
    }
}