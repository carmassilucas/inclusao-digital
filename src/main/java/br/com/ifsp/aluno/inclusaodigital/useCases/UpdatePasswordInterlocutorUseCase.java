package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.UpdatePasswordInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.entities.InterlocutorEntity;
import br.com.ifsp.aluno.inclusaodigital.exceptions.InterlocutorNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.exceptions.PasswordsNotMatchException;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePasswordInterlocutorUseCase {
    private final InterlocutorRepository interlocutorRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdatePasswordInterlocutorUseCase(
            InterlocutorRepository interlocutorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.interlocutorRepository = interlocutorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public InterlocutorEntity execute(
            UpdatePasswordInterlocutorRequestDto updatePasswordInterlocutorRequestDto,
            UUID interlocutorId
    ) {
        var interlocutor = this.interlocutorRepository.findById(interlocutorId)
                .orElseThrow(InterlocutorNotFoundException::new);

        var passwordMatches = updatePasswordInterlocutorRequestDto.newPassword()
                .equals(updatePasswordInterlocutorRequestDto.confirmPassword());

        if (!passwordMatches)
            throw new PasswordsNotMatchException();

        var correctPassword = passwordEncoder.matches(
                updatePasswordInterlocutorRequestDto.currentPassword(),
                interlocutor.getPassword()
        );

        if (!correctPassword)
            throw new InterlocutorNotFoundException();

        var encodedPassword = passwordEncoder.encode(updatePasswordInterlocutorRequestDto.newPassword().trim());

        interlocutor.setPassword(encodedPassword);

        return this.interlocutorRepository.saveAndFlush(interlocutor);
    }
}
