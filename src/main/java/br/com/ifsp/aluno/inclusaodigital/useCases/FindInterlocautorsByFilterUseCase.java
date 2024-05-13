package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.FindInterlocutorsByFilterRequestDto;
import br.com.ifsp.aluno.inclusaodigital.dtos.FindInterlocutorsByFilterResponseDto;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FindInterlocautorsByFilterUseCase {
    private final InterlocutorRepository interlocutorRepository;

    public FindInterlocautorsByFilterUseCase(InterlocutorRepository interlocutorRepository) {
        this.interlocutorRepository = interlocutorRepository;
    }

    public List<FindInterlocutorsByFilterResponseDto> execute(
            FindInterlocutorsByFilterRequestDto findInterlocutorsByFilterRequestDto,
            UUID uuid
    ) {
        var interlocutorsEntity = this.interlocutorRepository.findInterlocutorsByFilter(
                findInterlocutorsByFilterRequestDto.state(),
                findInterlocutorsByFilterRequestDto.city(),
                findInterlocutorsByFilterRequestDto.profiles(),
                uuid
        );

        return interlocutorsEntity.stream().map(interlocutor ->
                FindInterlocutorsByFilterResponseDto.builder()
                        .uuid(interlocutor.getUuid())
                        .name(interlocutor.getName())
                        .aboutMe(interlocutor.getAboutMe())
                        .dateOfBirth(interlocutor.getDateOfBirth())
                        .currentState(interlocutor.getCurrentState())
                        .currentCity(interlocutor.getCurrentCity())
                        .profilePicture(interlocutor.getProfilePicture())
                        .build()
                ).toList();
    }
}
