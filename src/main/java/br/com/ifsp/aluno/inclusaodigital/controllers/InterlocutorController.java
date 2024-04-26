package br.com.ifsp.aluno.inclusaodigital.controllers;

import br.com.ifsp.aluno.inclusaodigital.dtos.CreateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.useCases.CreateInterlocutorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interlocutor")
public class InterlocutorController {
    final CreateInterlocutorUseCase createInterlocutorUseCase;

    public InterlocutorController(CreateInterlocutorUseCase createInterlocutorUseCase) {
        this.createInterlocutorUseCase = createInterlocutorUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateInterlocutorRequestDto createInterlocutorRequestDto) {
        try {
            var interlocutor = this.createInterlocutorUseCase.execute(createInterlocutorRequestDto);
            return ResponseEntity.status(201).body(interlocutor);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
