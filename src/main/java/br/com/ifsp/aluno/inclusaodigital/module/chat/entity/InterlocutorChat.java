package br.com.ifsp.aluno.inclusaodigital.module.chat.entity;

import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity.Interlocutor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_interlocutor_chat")
public class InterlocutorChat implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private InterlocutorChatId id;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("interlocutorId")
    @JoinColumn(name = "interlocutor_id")
    private Interlocutor interlocutor;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("chatId")
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @CreationTimestamp
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    public InterlocutorChat() {
    }

    public InterlocutorChat(InterlocutorChatId id, Interlocutor interlocutor, Chat chat, LocalDateTime joinedAt) {
        this.id = id;
        this.interlocutor = interlocutor;
        this.chat = chat;
        this.joinedAt = joinedAt;
    }

    public InterlocutorChat(InterlocutorChatId id, Interlocutor interlocutor, Chat chat) {
        this.id = id;
        this.interlocutor = interlocutor;
        this.chat = chat;
    }

    public InterlocutorChatId getId() {
        return id;
    }

    public void setId(InterlocutorChatId id) {
        this.id = id;
    }

    public Interlocutor getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(Interlocutor interlocutor) {
        this.interlocutor = interlocutor;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
