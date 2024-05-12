package br.com.ifsp.aluno.inclusaodigital.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordInterlocutorRequestDto(
        @NotBlank String currentPassword,
        @NotBlank String newPassword,
        @NotBlank String confirmPassword
) {
}
