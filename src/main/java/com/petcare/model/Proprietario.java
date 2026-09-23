package com.petcare.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

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
    private Long id;

    @UuidGenerator
    @Schema(description = "Identificador único do proprietário")
    private UUID uuid;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nome completo do proprietário", example = "Pedro Cassol")
    private String nome;

    @Column(length = 100)
    @Schema(description = "E-mail do proprietário", example = "pedro@email.com")
    private String email;

    @Column(length = 20)
    @Schema(description = "Telefone do proprietário", example = "(55) 99999-9999")
    private String telefone;

    @Column(length = 255)
    @Schema(description = "Endereço do proprietário", example = "Rua das Flores, 123")
    private String endereco;
}