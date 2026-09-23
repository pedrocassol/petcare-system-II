package com.petcare.controller;

import com.petcare.model.Proprietario;
import com.petcare.service.ProprietarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "200", description = "Proprietário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public Proprietario salvar(@RequestBody Proprietario proprietario) {
        return proprietarioService.salvar(proprietario);
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
    public List<Proprietario> listarTodos() {
        return proprietarioService.listarTodos();
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
    public Proprietario buscarPorId(
            @Parameter(description = "ID do proprietário", example = "2")
            @PathVariable Long id) {
        return proprietarioService.buscarPorId(id);
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
    public Proprietario atualizar(
            @Parameter(description = "ID do proprietário", example = "2")
            @PathVariable Long id,
            @RequestBody Proprietario proprietario) {
        return proprietarioService.atualizar(id, proprietario);
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
    public void deletar(
            @Parameter(description = "ID do proprietário", example = "2")
            @PathVariable Long id) {
        proprietarioService.deletar(id);
    }
}