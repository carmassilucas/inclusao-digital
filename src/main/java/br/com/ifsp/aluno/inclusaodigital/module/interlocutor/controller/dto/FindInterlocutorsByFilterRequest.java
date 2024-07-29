package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record FindInterlocutorsByFilterRequest(
        @NotBlank(message = "Campo estado deve ser preenchido para buscar")
        String state,

        @NotBlank(message = "Campo cidade deve ser preenchido para buscar")
        String city,

        @NotEmpty
        Set<@NotBlank String> interlocutorTypes
) {
}
