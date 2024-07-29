package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.repository;

import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity.Interlocutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface InterlocutorRepository extends JpaRepository<Interlocutor, UUID> {
    Optional<Interlocutor> findByEmail(String email);

    @Query(
            value = "select interlocutor.* " +
                    "from tb_interlocutor interlocutor " +
                    "left join tb_interlocutor_type type on interlocutor.interlocutor_type_id = type.id " +
                    "where interlocutor.current_state ilike %:state% " +
                    "and interlocutor.current_city ilike %:city% " +
                    "and type.description in :interlocutorTypes " +
                    "and interlocutor.id <> :id",
            nativeQuery = true
    )
    List<Interlocutor> findByFilters(
            @Param("state") String state,
            @Param("city") String city,
            @Param("interlocutorTypes") Set<String> interlocutorTypes,
            @Param("id") UUID id
    );
}
