package br.com.ifsp.aluno.inclusaodigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {
    private String name;
    private String aboutMe;
    private String email;
    private LocalDate dateOfBirth;
    private String currentState;
    private String currentCity;
    private String profilePicture;
}
