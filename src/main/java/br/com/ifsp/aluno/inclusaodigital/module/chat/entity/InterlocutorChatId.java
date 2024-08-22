package br.com.ifsp.aluno.inclusaodigital.module.chat.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class InterlocutorChatId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "interlocutor_id", nullable = false, updatable = false, unique = true)
    private UUID interlocutorId;

    @Column(name = "chat_id", nullable = false, updatable = false, unique = true)
    private UUID chatId;

    public InterlocutorChatId() {
    }

    public InterlocutorChatId(UUID interlocutorId, UUID chatId) {
        this.interlocutorId = interlocutorId;
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterlocutorChatId that)) return false;
        return Objects.equals(interlocutorId, that.interlocutorId) && Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interlocutorId, chatId);
    }
}
