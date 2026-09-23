package com.petcare.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consulta")
@Schema(description = "Representa uma consulta veterinária")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UuidGenerator
    @Schema(description = "Identificador único da consulta")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "id_pet", nullable = false)
    @Schema(description = "Pet da consulta")
    private Pet pet;

    @Column(name = "data_hora", nullable = false)
    @Schema(description = "Data e hora da consulta", example = "2026-09-01T14:30:00")
    private LocalDateTime dataHora;

    @Column(nullable = false, length = 100)
    @Schema(description = "Veterinário responsável", example = "Dr. João Silva")
    private String veterinario;

    @Column(length = 255)
    @Schema(description = "Descrição da consulta", example = "Avaliação de rotina")
    private String descricao;

    @Column(name = "valor_estimado", precision = 10, scale = 2)
    @Schema(description = "Valor estimado da consulta", example = "150.00")
    private BigDecimal valorEstimado;

    @Column(nullable = false, length = 30)
    @Schema(description = "Status da consulta", example = "Agendada")
    private String status;

    @Column(length = 255)
    @Schema(description = "Observações da consulta")
    private String observacoes;
}