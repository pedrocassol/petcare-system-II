package com.petcare.controller;

import com.petcare.model.Proprietario;
import com.petcare.service.ProprietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @Operation(
            summary = "Cadastrar proprietário",
            description = "Cadastra um novo proprietário no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proprietário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Proprietario> salvar(@Valid @RequestBody Proprietario proprietario) {
        Proprietario novoProprietario = proprietarioService.salvar(proprietario);
        return ResponseEntity.status(201).body(novoProprietario);
    }

    @Operation(
            summary = "Listar proprietários",
            description = "Retorna todos os proprietários cadastrados no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de proprietários retornada com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<Proprietario>> listarTodos() {
        List<Proprietario> proprietarios = proprietarioService.listarTodos();
        return ResponseEntity.ok(proprietarios);
    }

    @Operation(
            summary = "Buscar proprietário por ID",
            description = "Retorna um proprietário específico pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proprietário encontrado"),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Proprietario> buscarPorId(
            @Parameter(description = "ID do proprietário", example = "2")
            @PathVariable Long id) {

        Proprietario proprietario = proprietarioService.buscarPorId(id);
        return ResponseEntity.ok(proprietario);
    }

    @Operation(
            summary = "Atualizar proprietário",
            description = "Atualiza os dados de um proprietário existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proprietário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Proprietario> atualizar(
            @Parameter(description = "ID do proprietário", example = "2")
            @PathVariable Long id,
            @RequestBody Proprietario proprietario) {

        Proprietario proprietarioAtualizado = proprietarioService.atualizar(id, proprietario);
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @Operation(
            summary = "Excluir proprietário",
            description = "Exclui um proprietário pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proprietário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Proprietário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do proprietário", example = "2")
            @PathVariable Long id) {

        proprietarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}