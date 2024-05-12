package br.com.ifsp.aluno.inclusaodigital.controllers;

import br.com.ifsp.aluno.inclusaodigital.dtos.CreateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.dtos.FindInterlocutorsByFilterRequestDto;
import br.com.ifsp.aluno.inclusaodigital.dtos.UpdateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.useCases.CreateInterlocutorUseCase;
import br.com.ifsp.aluno.inclusaodigital.useCases.FindInterlocautorsByFilterUseCase;
import br.com.ifsp.aluno.inclusaodigital.useCases.UpdateInterlocutorUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/interlocutor")
public class InterlocutorController {
    private final CreateInterlocutorUseCase createInterlocutorUseCase;
    private final FindInterlocautorsByFilterUseCase findInterlocautorsByFilterUseCase;
    private final UpdateInterlocutorUseCase updateInterlocutorUseCase;

    public InterlocutorController(
            CreateInterlocutorUseCase createInterlocutorUseCase,
            FindInterlocautorsByFilterUseCase findInterlocautorsByFilterUseCase,
            UpdateInterlocutorUseCase updateInterlocutorUseCase
    ) {
        this.createInterlocutorUseCase = createInterlocutorUseCase;
        this.findInterlocautorsByFilterUseCase = findInterlocautorsByFilterUseCase;
        this.updateInterlocutorUseCase = updateInterlocutorUseCase;
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
        var interlocutors = this.findInterlocautorsByFilterUseCase.execute(
                findInterlocutorsByFilterRequestDto, uuid
        );

        return ResponseEntity.ok().body(interlocutors);
    }

    @PutMapping
    public ResponseEntity<Object> update(
            @RequestBody UpdateInterlocutorRequestDto updateInterlocutorRequestDto,
            HttpServletRequest httpServletRequest
    ) {
        var uuid = UUID.fromString((String) httpServletRequest.getAttribute("interlocutor_id"));

        var updatedInterlocutor = this.updateInterlocutorUseCase.execute(
                updateInterlocutorRequestDto, uuid
        );

        return ResponseEntity.ok().body(updatedInterlocutor);
    }
}
