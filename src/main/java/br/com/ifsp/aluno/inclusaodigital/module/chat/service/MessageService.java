package br.com.ifsp.aluno.inclusaodigital.module.chat.service;

import br.com.ifsp.aluno.inclusaodigital.exceptions.MessageNotFoundException;
import br.com.ifsp.aluno.inclusaodigital.module.chat.controller.dto.SendMessageRequest;
import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.Message;
import br.com.ifsp.aluno.inclusaodigital.module.chat.repository.MessageRepository;
import br.com.ifsp.aluno.inclusaodigital.module.interlocutor.entity.Interlocutor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessageService {

    private final ChatService chatService;
    private final MessageRepository messageRepository;

    public MessageService(ChatService chatService, MessageRepository messageRepository) {
        this.chatService = chatService;
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(UUID chatId) {
        return messageRepository.findByChatId(chatId);
    }

    @Transactional
    public void send(SendMessageRequest payload, UUID interlocutorSenderId) {
        var chat = this.chatService.get(payload, interlocutorSenderId);

        Message replyTo = null;

        if (payload.replyToId() != null)
            replyTo = this.messageRepository.findById(payload.replyToId()).orElseThrow(MessageNotFoundException::new);

        var sender = new Interlocutor();
        sender.setId(interlocutorSenderId);

        var message = new Message(payload.content(), replyTo, sender, chat);

        this.messageRepository.save(message);
    }

    public void readMessages(List<UUID> payload) {
        this.messageRepository.readMessages(payload);
    }
}