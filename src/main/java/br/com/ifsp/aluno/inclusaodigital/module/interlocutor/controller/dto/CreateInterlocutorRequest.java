package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.controller.dto;

import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity.Interlocutor;
import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity.InterlocutorType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateInterlocutorRequest(
        @NotNull String name,
        String aboutMe,
        @NotNull @Email String email,
        @NotNull() @Length(min = 10, max = 100) String password,
        @NotNull LocalDate dateOfBirth,
        @NotNull String currentState,
        @NotNull String currentCity,
        @NotNull InterlocutorType.Values intinterlocutorType
) {
        public Interlocutor toInterlocutor() {
                return new Interlocutor(
                        name,
                        aboutMe,
                        email,
                        dateOfBirth,
                        currentState,
                        currentCity,
                        intinterlocutorType
                );
        }
}
