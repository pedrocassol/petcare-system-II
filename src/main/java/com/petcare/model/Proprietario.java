package com.petcare.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import jakarta.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proprietario")
@Schema(description = "Representa um proprietário cadastrado no sistema")
public class Proprietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @UuidGenerator
    @Schema(description = "Identificador único do proprietário")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nome completo do proprietário", example = "Pedro Cassol")
    @NotBlank
    private String nome;

    @Column(length = 100)
    @Schema(description = "E-mail do proprietário", example = "pedro@email.com")
    @Email
    private String email;

    @Column(length = 20)
    @Schema(description = "Telefone do proprietário", example = "(55) 99999-9999")
    @Size(max = 20)
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXXX-XXXX"
    )
    private String telefone;

    @Embedded
    @Schema(description = "Endereço do proprietário")
    private Endereco endereco;

    @OneToMany(mappedBy = "proprietario")
    @JsonIgnore
    private List<Pet> pets;
}