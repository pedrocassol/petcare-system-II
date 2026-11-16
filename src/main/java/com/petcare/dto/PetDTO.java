package com.petcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private Integer idade;
}
