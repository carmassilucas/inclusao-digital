package br.com.ifsp.aluno.inclusaodigital.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FindInterlocutorsByFilterRequestDto(
        @NotBlank(message = "Campo estado deve ser preenchido para buscar")
        String state,

        @NotBlank(message = "Campo cidade deve ser preenchido para buscar")
        String city,

        @NotEmpty
        List<String> profiles
) {
}
