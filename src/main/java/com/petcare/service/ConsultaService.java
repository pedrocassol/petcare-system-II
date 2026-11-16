package com.petcare.service;

import com.petcare.model.Consulta;
import com.petcare.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import com.petcare.infra.ConsultaNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNotFoundException(
                        "Consulta não encontrada para o ID: " + id
                ));
    }

    @Transactional
    public Consulta atualizar(Long id, Consulta consulta) {
        Consulta existente = consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNotFoundException(
                        "Consulta não encontrada para o ID: " + id
                ));

        existente.setPet(consulta.getPet());
        existente.setDataHora(consulta.getDataHora());
        existente.setVeterinario(consulta.getVeterinario());
        existente.setDescricao(consulta.getDescricao());
        existente.setValorEstimado(consulta.getValorEstimado());
        existente.setStatus(consulta.getStatus());
        existente.setObservacoes(consulta.getObservacoes());

        return existente;
    }

    public void deletar(Long id) {
        consultaRepository.findById(id)
                .orElseThrow(() -> new ConsultaNotFoundException(
                        "Consulta não encontrada para o ID: " + id
                ));

        consultaRepository.deleteById(id);
    }
}