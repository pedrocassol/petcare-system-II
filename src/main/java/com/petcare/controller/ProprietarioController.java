package com.petcare.controller;

import com.petcare.model.Proprietario;
import com.petcare.service.ProprietarioService;
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
@RequestMapping("/proprietarios")
@Tag(
        name = "Proprietários",
        description = "Operações relacionadas aos proprietários"
)
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
            @ApiResponse(
                    responseCode = "201",
                    description = "Proprietário cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Proprietario.class)
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
    public ResponseEntity<Proprietario> salvar(
            @Valid @RequestBody Proprietario proprietario) {

        Proprietario novoProprietario = proprietarioService.salvar(proprietario);
        return ResponseEntity.status(201).body(novoProprietario);
    }

    @Operation(
            summary = "Listar proprietários",
            description = "Retorna todos os proprietários cadastrados no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de proprietários retornada com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = Proprietario.class)
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<Proprietario>> listarTodos() {
        List<Proprietario> proprietarios = proprietarioService.listarTodos();
        return ResponseEntity.ok(proprietarios);
    }

    @Operation(
            summary = "Buscar proprietário por UUID",
            description = "Retorna um proprietário específico pelo seu UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Proprietário encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Proprietario.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proprietário não encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<Proprietario> buscarPorUuid(
            @Parameter(
                    description = "UUID do proprietário",
                    example = "e5a8a8c9-073e-4419-a662-ea0c1b825a73"
            )
            @PathVariable UUID uuid) {

        Proprietario proprietario = proprietarioService.buscarPorUuid(uuid);
        return ResponseEntity.ok(proprietario);
    }

    @Operation(
            summary = "Atualizar proprietário",
            description = "Atualiza os dados de um proprietário existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Proprietário atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Proprietario.class)
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
                    description = "Proprietário não encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<Proprietario> atualizar(
            @Parameter(description = "UUID do proprietário")
            @PathVariable UUID uuid,
            @Valid @RequestBody Proprietario proprietario) {

        Proprietario proprietarioAtualizado =
                proprietarioService.atualizarPorUuid(uuid, proprietario);

        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @Operation(
            summary = "Excluir proprietário",
            description = "Exclui um proprietário pelo seu UUID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Proprietário excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Proprietário não encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "UUID do proprietário")
            @PathVariable UUID uuid) {

        proprietarioService.deletarPorUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
