package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.CreateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.entities.InterlocutorEntity;
import br.com.ifsp.aluno.inclusaodigital.exceptions.EmailAlreadyRegisteredException;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateInterlocutorUseCase {
    final InterlocutorRepository interlocutorRepository;
    final PasswordEncoder passwordEncoder;

    public CreateInterlocutorUseCase(
            InterlocutorRepository interlocutorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.interlocutorRepository = interlocutorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public InterlocutorEntity execute(CreateInterlocutorRequestDto createInterlocutorRequestDto) {
        this.interlocutorRepository.findByEmail(createInterlocutorRequestDto.email())
                .ifPresent((interlocutor) -> {
                    throw new EmailAlreadyRegisteredException();
                });

        var passwordEncoded = passwordEncoder.encode(createInterlocutorRequestDto.password());

        var interlocutor = InterlocutorEntity.builder()
                .name(createInterlocutorRequestDto.name())
                .email(createInterlocutorRequestDto.email())
                .password(passwordEncoded)
                .dateOfBirth(createInterlocutorRequestDto.dateOfBirth())
                .currentState(createInterlocutorRequestDto.currentState())
                .currentCity(createInterlocutorRequestDto.currentCity())
                .build();

        return this.interlocutorRepository.save(interlocutor);
    }
}
