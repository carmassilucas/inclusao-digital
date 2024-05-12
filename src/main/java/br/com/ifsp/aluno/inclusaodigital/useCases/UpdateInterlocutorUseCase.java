package br.com.ifsp.aluno.inclusaodigital.useCases;

import br.com.ifsp.aluno.inclusaodigital.dtos.UpdateInterlocutorRequestDto;
import br.com.ifsp.aluno.inclusaodigital.entities.InterlocutorEntity;
import br.com.ifsp.aluno.inclusaodigital.exceptions.InterlocutorNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.repositories.InterlocutorRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

@Service
public class UpdateInterlocutorUseCase {

    private final InterlocutorRepository interlocutorRepository;

    public UpdateInterlocutorUseCase(InterlocutorRepository interlocutorRepository) {
        this.interlocutorRepository = interlocutorRepository;
    }

    public InterlocutorEntity execute(
            UpdateInterlocutorRequestDto updateInterlocutorRequestDto,
            UUID uuid
    ) {
        var intelocutor = this.interlocutorRepository.findById(uuid)
                .orElseThrow(InterlocutorNotFoundException::new);

        var updatedInterlocutor = copyNonNullProperties(intelocutor, updateInterlocutorRequestDto);

        return this.interlocutorRepository.saveAndFlush(updatedInterlocutor);
    }

    private InterlocutorEntity copyNonNullProperties(
            InterlocutorEntity interlocutorEntity,
            UpdateInterlocutorRequestDto updateInterlocutorRequestDto
    ) {
        Arrays.stream(UpdateInterlocutorRequestDto.class.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);

            try {
                Object value = field.get(updateInterlocutorRequestDto);

                if (value != null && !value.toString().trim().isEmpty()) {
                    Field interlocutorField = InterlocutorEntity.class.getDeclaredField(field.getName());
                    interlocutorField.setAccessible(true);
                    interlocutorField.set(interlocutorEntity, value);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        });

        return interlocutorEntity;
    }
}
