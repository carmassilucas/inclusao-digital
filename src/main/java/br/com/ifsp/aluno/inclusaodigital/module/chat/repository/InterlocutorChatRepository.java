package br.com.ifsp.aluno.inclusaodigital.module.chat.repository;

import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.InterlocutorChat;
import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.InterlocutorChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InterlocutorChatRepository extends JpaRepository<InterlocutorChat, InterlocutorChatId> {
    List<InterlocutorChat> findByInterlocutorId(UUID interlocutorId);
}
