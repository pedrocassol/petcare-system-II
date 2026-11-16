package com.petcare.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class PetPatchDTO {

    @Pattern(regexp = ".*\\S.*", message = "Nome não pode estar vazio")
    private String nome;

    @Pattern(regexp = ".*\\S.*", message = "Espécie não pode estar vazia")
    private String especie;

    @Size(max = 100)
    private String raca;

    @Min(value = 0, message = "Idade não pode ser negativa")
    private Integer idade;

    @Size(max = 20)
    private String sexo;

    @Size(max = 255)
    private String observacoes;
}
