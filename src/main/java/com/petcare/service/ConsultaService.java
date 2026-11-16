
package com.petcare.service;

import com.petcare.infra.ConsultaNotFoundException;
import com.petcare.infra.PetNotFoundException;
import com.petcare.model.Consulta;
import com.petcare.model.Pet;
import com.petcare.repository.ConsultaRepository;
import com.petcare.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PetRepository petRepository;

    public ConsultaService(
            ConsultaRepository consultaRepository,
            PetRepository petRepository) {

        this.consultaRepository = consultaRepository;
        this.petRepository = petRepository;
    }

    @Transactional
    public Consulta salvar(Consulta consulta) {
        Pet pet = buscarPetPorId(consulta.getPet().getId());

        consulta.setPet(pet);

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

        Pet pet = buscarPetPorId(consulta.getPet().getId());

        existente.setPet(pet);
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

    public Consulta buscarPorUuid(UUID uuid) {
        return consultaRepository.findByUuid(uuid)
                .orElseThrow(() -> new ConsultaNotFoundException(
                        "Consulta não encontrada para o UUID: " + uuid
                ));
    }

    @Transactional
    public Consulta atualizarPorUuid(UUID uuid, Consulta consulta) {
        Consulta existente = buscarPorUuid(uuid);

        Pet pet = buscarPetPorId(consulta.getPet().getId());

        existente.setPet(pet);
        existente.setDataHora(consulta.getDataHora());
        existente.setVeterinario(consulta.getVeterinario());
        existente.setDescricao(consulta.getDescricao());
        existente.setValorEstimado(consulta.getValorEstimado());
        existente.setStatus(consulta.getStatus());
        existente.setObservacoes(consulta.getObservacoes());

        return existente;
    }

    public void deletarPorUuid(UUID uuid) {
        Consulta existente = buscarPorUuid(uuid);
        consultaRepository.deleteById(existente.getId());
    }

    private Pet buscarPetPorId(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new PetNotFoundException(
                        "Pet não encontrado para o ID: " + id
                ));
    }
}
