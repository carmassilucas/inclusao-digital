package br.com.ifsp.aluno.inclusaodigital.dtos;

import jakarta.validation.constraints.NotBlank;

public record ListInterlocutorsByFilterRequestDto(
        @NotBlank(message = "Campo estado deve ser preenchido para buscar")
        String state,

        @NotBlank(message = "Campo cidade deve ser preenchido para buscar")
        String city
) {
}
