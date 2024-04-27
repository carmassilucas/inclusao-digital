package br.com.ifsp.aluno.inclusaodigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListInterlocutorsByFilterResponseDto {
    private UUID uuid;
    private String name;
    private String aboutMe;
    private LocalDate dateOfBirth;
    private String currentState;
    private String currentCity;
    private String profilePicture;
}
