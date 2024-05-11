package br.com.ifsp.aluno.inclusaodigital.controllers;

import br.com.ifsp.aluno.inclusaodigital.dtos.CreateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.dtos.FindInterlocutorsByFilterRequestDto;
import br.com.ifsp.aluno.inclusaodigital.useCases.CreateInterlocutorUseCase;
import br.com.ifsp.aluno.inclusaodigital.useCases.ListInterlocautorsByFilterUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/interlocutor")
public class InterlocutorController {
    private final CreateInterlocutorUseCase createInterlocutorUseCase;
    private final ListInterlocautorsByFilterUseCase listInterlocautorsByFilterUseCase;

    public InterlocutorController(
            CreateInterlocutorUseCase createInterlocutorUseCase,
            ListInterlocautorsByFilterUseCase listInterlocautorsByFilterUseCase
    ) {
        this.createInterlocutorUseCase = createInterlocutorUseCase;
        this.listInterlocautorsByFilterUseCase = listInterlocautorsByFilterUseCase;
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
    public ResponseEntity<Object> findByFilter(
            @Valid @RequestBody FindInterlocutorsByFilterRequestDto findInterlocutorsByFilterRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        var uuid = UUID.fromString((String) httpServletRequest.getAttribute("interlocutor_id"));
        var interlocutors = this.listInterlocautorsByFilterUseCase.execute(
                findInterlocutorsByFilterRequestDto, uuid
        );

        return ResponseEntity.ok().body(interlocutors);
    }
}
