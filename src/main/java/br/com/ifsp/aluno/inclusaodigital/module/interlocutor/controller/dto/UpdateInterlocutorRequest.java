package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.controller.dto;

import java.time.LocalDate;

public record UpdateInterlocutorRequest(
        String name,
        String aboutMe,
        LocalDate dateOfBirth,
        String currentState,
        String currentCity
) {

}
