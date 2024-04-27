package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.ListInterlocutorsByFilterRequestDto;
import br.com.ifsp.aluno.inclusaodigital.dtos.ListInterlocutorsByFilterResponseDto;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListInterlocautorsByFilterUseCase {
    private final InterlocutorRepository interlocutorRepository;

    public ListInterlocautorsByFilterUseCase(InterlocutorRepository interlocutorRepository) {
        this.interlocutorRepository = interlocutorRepository;
    }

    public List<ListInterlocutorsByFilterResponseDto> execute(
            ListInterlocutorsByFilterRequestDto listInterlocutorsByFilterRequestDto,
            UUID uuid
    ) {
        var interlocutorsEntity = this.interlocutorRepository.findInterlocutorsByFilter(
            listInterlocutorsByFilterRequestDto.state(),
            listInterlocutorsByFilterRequestDto.city(),
            uuid
        );

        return interlocutorsEntity.stream().map(interlocutor ->
                ListInterlocutorsByFilterResponseDto.builder()
                        .uuid(interlocutor.getUuid())
                        .name(interlocutor.getName())
                        .dateOfBirth(interlocutor.getDateOfBirth())
                        .currentState(interlocutor.getCurrentState())
                        .currentCity(interlocutor.getCurrentCity())
                        .profilePicture(interlocutor.getProfilePicture())
                        .build()
                ).toList();
    }
}
