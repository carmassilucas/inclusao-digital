package br.com.ifsp.aluno.inclusaodigital.controllers;

import br.com.ifsp.aluno.inclusaodigital.dtos.CreateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.useCases.CreateInterlocutorUseCase;
import br.com.ifsp.aluno.inclusaodigital.useCases.ListAllInterlocautorsUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interlocutor")
public class InterlocutorController {
    private final CreateInterlocutorUseCase createInterlocutorUseCase;
    private final ListAllInterlocautorsUseCase listAllInterlocautorsUseCase;

    public InterlocutorController(
            CreateInterlocutorUseCase createInterlocutorUseCase,
            ListAllInterlocautorsUseCase listAllInterlocautorsUseCase
    ) {
        this.createInterlocutorUseCase = createInterlocutorUseCase;
        this.listAllInterlocautorsUseCase = listAllInterlocautorsUseCase;
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

    @GetMapping
    public ResponseEntity<Object> listAll() {
        var interlocutors = this.listAllInterlocautorsUseCase.execute();
        return ResponseEntity.ok().body(interlocutors);
    }
}
