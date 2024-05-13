package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.ProfileResponseDto;
import br.com.ifsp.aluno.inclusaodigital.exceptions.InterlocutorNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileInterlocutorUseCase {
    private final InterlocutorRepository interlocutorRepository;

    public ProfileInterlocutorUseCase(InterlocutorRepository interlocutorRepository) {
        this.interlocutorRepository = interlocutorRepository;
    }

    public ProfileResponseDto execute(UUID interlocutorId) {
        var interlocutor = this.interlocutorRepository.findById(interlocutorId)
                .orElseThrow(InterlocutorNotFoundException::new);

        return ProfileResponseDto.builder()
                .name(interlocutor.getName())
                .aboutMe(interlocutor.getAboutMe())
                .email(interlocutor.getEmail())
                .dateOfBirth(interlocutor.getDateOfBirth())
                .currentState(interlocutor.getCurrentState())
                .currentCity(interlocutor.getCurrentCity())
                .profilePicture(interlocutor.getProfilePicture())
                .build();
    }
}
