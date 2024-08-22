package br.com.ifsp.aluno.inclusaodigital.module.chat.service;

import br.com.ifsp.aluno.inclusaodigital.exceptions.ChatNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.exceptions.InterlocutorNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.module.chat.controller.dto.SendMessageRequest;
import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.Chat;
import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.InterlocutorChat;
import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.InterlocutorChatId;
import br.com.ifsp.aluno.inclusaodigital.module.chat.repository.ChatRepository;
import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.repository.InterlocutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final InterlocutorRepository interlocutorRepository;

    public ChatService(ChatRepository chatRepository, InterlocutorRepository interlocutorRepository) {
        this.chatRepository = chatRepository;
        this.interlocutorRepository = interlocutorRepository;
    }

    public List<Chat> getChats(UUID id) {
        return this.chatRepository.findByInterlocutorId(id);
    }

    public Chat get(UUID id) {
        return this.chatRepository.findById(id).orElseThrow(ChatNotFoundException::new);
    }

    @Transactional
    public Chat get(SendMessageRequest payload, UUID interlocutorSenderId) {
        var chatInterlocutorsIds = Set.of(interlocutorSenderId, payload.receiverId());

        return this.chatRepository.findByInterlocutorsIds(chatInterlocutorsIds)
                .orElseGet(() -> {
                    var chat = new Chat();

                    var relationship = chatInterlocutorsIds.stream().map(uuid -> {
                        var interlocutor = this.interlocutorRepository.findById(uuid)
                                .orElseThrow(InterlocutorNotFoundException::new);

                        return new InterlocutorChat(new InterlocutorChatId(uuid, chat.getId()), interlocutor, chat);
                    }).collect(Collectors.toSet());

                    chat.setInterlocutorChats(relationship);

                    return this.chatRepository.save(chat);
                });
    }
}
