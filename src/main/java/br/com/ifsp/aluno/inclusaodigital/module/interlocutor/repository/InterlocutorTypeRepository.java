package br.com.ifsp.aluno.inclusaodigital.module.interlocutor.repository;

import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity.InterlocutorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterlocutorTypeRepository extends JpaRepository<InterlocutorType, Long> {
}
