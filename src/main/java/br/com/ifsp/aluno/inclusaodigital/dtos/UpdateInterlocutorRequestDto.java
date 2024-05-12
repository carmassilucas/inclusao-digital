package br.com.ifsp.aluno.inclusaodigital.dtos;

import java.time.LocalDate;

public record UpdateInterlocutorRequestDto(
        String name,
        String aboutMe,
        LocalDate dateOfBirth,
        String currentState,
        String currentCity
) {

}
