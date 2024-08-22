package br.com.ifsp.aluno.inclusaodigital.module.chat.service;

import br.com.ifsp.aluno.inclusaodigital.module.chat.entity.InterlocutorChat;
import br.com.ifsp.aluno.inclusaodigital.module.chat.repository.InterlocutorChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InterlocutorChatService {

    private final InterlocutorChatRepository interlocutorChatRepository;

    public InterlocutorChatService(InterlocutorChatRepository interlocutorChatRepository) {
        this.interlocutorChatRepository = interlocutorChatRepository;
    }

    public List<InterlocutorChat> getChats(UUID interlocutorId) {
        return this.interlocutorChatRepository.findByInterlocutorId(interlocutorId);
    }
}
