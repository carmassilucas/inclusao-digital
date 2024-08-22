package br.com.ifsp.aluno.inclusaodigital.module.chat.repository;

import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByChatId(UUID chatId);

    @Query(
            value = "update tb_message set read = true where id in :payload",
            nativeQuery = true
    )
    @Modifying
    @Transactional
    void readMessages(List<UUID> payload);
}
