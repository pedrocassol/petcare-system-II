package com.petcare.service;

import com.petcare.model.Consulta;
import com.petcare.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id).orElse(null);
    }

    public Consulta atualizar(Long id, Consulta consulta) {
        consulta.setId(id);
        return consultaRepository.save(consulta);
    }

    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }
}