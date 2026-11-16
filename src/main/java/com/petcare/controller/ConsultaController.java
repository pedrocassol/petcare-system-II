package com.petcare.controller;

import com.petcare.model.Consulta;
import com.petcare.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@Tag(
        name = "Consultas",
        description = "Operações relacionadas às consultas veterinárias"
)
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
            @ApiResponse(
                    responseCode = "201",
                    description = "Consulta cadastrada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Consulta.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Consulta> salvar(
            @Valid @RequestBody Consulta consulta) {

        Consulta novaConsulta = consultaService.salvar(consulta);
        return ResponseEntity.status(201).body(novaConsulta);
    }

    @Operation(
            summary = "Listar consultas",
            description = "Retorna todas as consultas cadastradas no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de consultas retornada com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = Consulta.class)
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodos() {
        List<Consulta> consultas = consultaService.listarTodos();
        return ResponseEntity.ok(consultas);
    }

    @Operation(
            summary = "Buscar consulta por ID",
            description = "Retorna uma consulta específica pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Consulta.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Consulta não encontrada",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<Consulta> buscarPorUuid(
            @Parameter(description = "UUID da consulta")
            @PathVariable UUID uuid) {

        Consulta consulta = consultaService.buscarPorUuid(uuid);
        return ResponseEntity.ok(consulta);
    }

    @Operation(
            summary = "Atualizar consulta",
            description = "Atualiza os dados de uma consulta existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Consulta atualizada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Consulta.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Consulta não encontrada",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<Consulta> atualizar(
            @Parameter(description = "UUID da consulta")
            @PathVariable UUID uuid,
            @Valid @RequestBody Consulta consulta) {

        Consulta consultaAtualizada =
                consultaService.atualizarPorUuid(uuid, consulta);

        return ResponseEntity.ok(consultaAtualizada);
    }

    @Operation(
            summary = "Excluir consulta",
            description = "Exclui uma consulta pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Consulta excluída com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Consulta não encontrada",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "UUID da consulta")
            @PathVariable UUID uuid) {

        consultaService.deletarPorUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
