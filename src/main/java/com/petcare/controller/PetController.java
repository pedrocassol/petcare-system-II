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
import com.petcare.dto.PetDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import java.util.UUID;

import java.util.List;

@RestController
@RequestMapping("/pets")
@Tag(
        name = "Pets",
        description = "Operações relacionadas aos pets"
)
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
            @ApiResponse(
                    responseCode = "201",
                    description = "Pet cadastrado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class)
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
            description = "Lista de pets retornada com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(implementation = Pet.class)
                    )
            )
    )
    @GetMapping
    public ResponseEntity<List<Pet>> listarTodos() {
        List<Pet> pets = petService.listarTodos();
        return ResponseEntity.ok(pets);
    }

    @Operation(
            summary = "Listar pets por proprietário",
            description = "Retorna os pets associados a um proprietário"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pets encontrados",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Pet.class)
                            )
                    )
            )
    })
    @GetMapping("/proprietario/{uuid}")
    public ResponseEntity<List<Pet>> buscarPetsPorProprietario(
            @Parameter(description = "UUID do proprietário")
            @PathVariable UUID uuid) {

        List<Pet> pets = petService.buscarPetsPorProprietario(uuid);
        return ResponseEntity.ok(pets);
    }

    @Operation(
            summary = "Buscar pet por ID",
            description = "Retorna um pet específico pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pet encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PetDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pet não encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @GetMapping("/{uuid}")
    public ResponseEntity<PetDTO> buscarPorUuid(
            @Parameter(description = "UUID do pet")
            @PathVariable UUID uuid) {

        Pet pet = petService.buscarPorUuid(uuid);

        PetDTO petDTO = new PetDTO(
                pet.getUuid(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getIdade()
        );

        return ResponseEntity.ok(petDTO);
    }

    @Operation(
            summary = "Atualizar pet",
            description = "Atualiza os dados de um pet existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Pet atualizado com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class)
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
                    description = "Pet não encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PutMapping("/{uuid}")
    public ResponseEntity<Pet> atualizar(
            @Parameter(description = "UUID do pet")
            @PathVariable UUID uuid,
            @Valid @RequestBody Pet pet) {

        Pet petAtualizado = petService.atualizarPorUuid(uuid, pet);
        return ResponseEntity.ok(petAtualizado);
    }

    @Operation(
            summary = "Excluir pet",
            description = "Exclui um pet pelo seu ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Pet excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Pet não encontrado",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "UUID do pet")
            @PathVariable UUID uuid) {

        petService.deletarPorUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}