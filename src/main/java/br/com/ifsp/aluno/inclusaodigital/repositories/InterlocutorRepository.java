package br.com.ifsp.aluno.inclusaodigital.repositories;

import br.com.ifsp.aluno.inclusaodigital.entities.InterlocutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterlocutorRepository extends JpaRepository<InterlocutorEntity, UUID> {
    Optional<InterlocutorEntity> findByEmail(String email);
    @Query(
            "select itrlc " +
            "from interlocutor itrlc " +
            "where itrlc.currentState ilike %:state% " +
                    "and itrlc.currentCity ilike %:city% " +
                    "and itrlc.profile in :profiles " +
                    "and itrlc.uuid <> :uuid"
    )
    List<InterlocutorEntity> findInterlocutorsByFilter(
            @Param("state") String state,
            @Param("city") String city,
            @Param("profiles") List<String> profiles,
            @Param("uuid") UUID uuid
    );
}
