package br.com.ifsp.aluno.inclusaodigital.repositories;

import br.com.ifsp.aluno.inclusaodigital.entities.InterlocutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InterlocutorRepository extends JpaRepository<InterlocutorEntity, UUID> {
    Optional<InterlocutorEntity> findByEmail(String email);
}
