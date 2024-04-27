package br.com.ifsp.aluno.inclusaodigital.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthInterlocutorResponseDto {
    private String access_token;
    private Long expires_in;
}
