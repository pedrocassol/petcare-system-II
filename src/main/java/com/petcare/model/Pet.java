package com.petcare.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pet")
@Schema(description = "Representa um pet cadastrado no sistema")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    @Schema(description = "Identificador único do pet")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nome do pet", example = "Rex")
    @NotBlank
    private String nome;

    @Column(nullable = false, length = 50)
    @Schema(description = "Espécie do pet", example = "Cachorro")
    @NotBlank
    private String especie;

    @Column(length = 100)
    @Schema(description = "Raça do pet", example = "Golden Retriever")
    @Size(max = 100)
    private String raca;

    @Column
    @Schema(description = "Idade do pet", example = "5")
    @NotNull
    private Integer idade;

    @Column(length = 20)
    @Schema(description = "Sexo do pet", example = "Macho")
    @Size(max = 20)
    private String sexo;

    @Column(length = 255)
    @Schema(description = "Observações sobre o pet")
    @Size(max = 255)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_proprietario", nullable = false)
    @Schema(description = "Proprietário do pet")
    private Proprietario proprietario;
}