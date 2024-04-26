package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.ListAllInterlocutorsResponseDto;
import br.com.ifsp.aluno.inclusaodigital.entities.InterlocutorEntity;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllInterlocautorsUseCase {
    private final InterlocutorRepository interlocutorRepository;

    public ListAllInterlocautorsUseCase(InterlocutorRepository interlocutorRepository) {
        this.interlocutorRepository = interlocutorRepository;
    }

    public List<ListAllInterlocutorsResponseDto> execute() {
        var interlocutorsEntity = this.interlocutorRepository.findAll();

        return interlocutorsEntity.stream().map(interlocutor ->
                ListAllInterlocutorsResponseDto.builder()
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
