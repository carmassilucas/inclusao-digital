package br.com.ifsp.aluno.inclusaodigital.controllers;

import br.com.ifsp.aluno.inclusaodigital.dtos.AuthInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.useCases.AuthInterlocutorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/interlocutor/auth")
public class AuthInterlocutorController {
    private final AuthInterlocutorUseCase authInterlocutorUseCase;

    public AuthInterlocutorController(AuthInterlocutorUseCase authInterlocutorUseCase) {
        this.authInterlocutorUseCase = authInterlocutorUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> auth(@RequestBody AuthInterlocutorRequestDto authInterlocutorRequestDto) {
        try {
            var token = this.authInterlocutorUseCase.execute(authInterlocutorRequestDto);
            return ResponseEntity.ok().body(token);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
