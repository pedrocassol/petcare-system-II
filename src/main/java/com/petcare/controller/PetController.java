package com.petcare.controller;

import com.petcare.model.Pet;
import com.petcare.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            summary = "Cadastrar pet",
            description = "Cadastra um novo pet no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pet cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<Pet> salvar(@Valid @RequestBody Pet pet) {
        Pet novoPet = petService.salvar(pet);
        return ResponseEntity.status(201).body(novoPet);
    }

    @Operation(
            summary = "Listar pets",
            description = "Retorna todos os pets cadastrados no sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de pets retornada com sucesso"
    )
    @GetMapping
    public ResponseEntity<List<Pet>> listarTodos() {
        List<Pet> pets = petService.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @Operation(
            summary = "Buscar pet por ID",
            description = "Retorna um pet específico pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet encontrado"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPorId(
            @Parameter(description = "ID do pet", example = "4")
            @PathVariable Long id) {

        Pet pet = petService.buscarPorId(id);
        return ResponseEntity.ok(pet);
    }

    @Operation(
            summary = "Atualizar pet",
            description = "Atualiza os dados de um pet existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizar(
            @Parameter(description = "ID do pet", example = "4")
            @PathVariable Long id,
            @RequestBody Pet pet) {

        Pet petAtualizado = petService.atualizar(id, pet);
        return ResponseEntity.ok(petAtualizado);
    }

    @Operation(
            summary = "Excluir pet",
            description = "Exclui um pet pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pet excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pet não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do pet", example = "4")
            @PathVariable Long id) {

        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}