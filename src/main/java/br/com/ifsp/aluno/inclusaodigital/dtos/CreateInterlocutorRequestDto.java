package br.com.ifsp.aluno.inclusaodigital.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateInterlocutorRequestDto(
        @NotNull(message = "Preencha o campo nome para cadastrar um interlocutor")
        String name,

        @Email(message = "Endereço de e-mail inválido")
        @NotNull(message = "Preencha o campo e-mail para cadastrar um interlocutor")
        String email,

        @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres")
        @NotNull(message = "Preencha o campo senha para cadastrar um  interlocutor")
        String password,

        @NotNull(message = "Preencha o campo data de nascimento para cadastrar um interlocutor")
        LocalDate dateOfBirth,

        @NotNull(message = "Preencha o campo estado atual para cadastrar um interlocutor")
        String currentState,

        @NotNull(message = "Preencha o campo cidade atual para cadastrar um interlocutor")
        String currentCity
) {

}
