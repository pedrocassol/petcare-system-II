package com.petcare.controller;

import com.petcare.model.Consulta;
import com.petcare.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @Operation(
            summary = "Cadastrar consulta",
            description = "Cadastra uma nova consulta no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public Consulta salvar(@RequestBody Consulta consulta) {
        return consultaService.salvar(consulta);
    }

    @Operation(
            summary = "Listar consultas",
            description = "Retorna todas as consultas cadastradas no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de consultas retornada com sucesso"
    )
    @GetMapping
    public List<Consulta> listarTodos() {
        return consultaService.listarTodos();
    }

    @Operation(
            summary = "Buscar consulta por ID",
            description = "Retorna uma consulta específica pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta encontrada"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @GetMapping("/{id}")
    public Consulta buscarPorId(
            @Parameter(description = "ID da consulta", example = "1")
            @PathVariable Long id) {
        return consultaService.buscarPorId(id);
    }

    @Operation(
            summary = "Atualizar consulta",
            description = "Atualiza os dados de uma consulta existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @PutMapping("/{id}")
    public Consulta atualizar(
            @Parameter(description = "ID da consulta", example = "1")
            @PathVariable Long id,
            @RequestBody Consulta consulta) {
        return consultaService.atualizar(id, consulta);
    }

    @Operation(
            summary = "Excluir consulta",
            description = "Exclui uma consulta pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @DeleteMapping("/{id}")
    public void deletar(
            @Parameter(description = "ID da consulta", example = "1")
            @PathVariable Long id) {
        consultaService.deletar(id);
    }
}