CREATE TABLE tb_chat (
    id UUID PRIMARY KEY,
    name CHARACTER VARYING,
    description TEXT,
    profile_picture CHARACTER VARYING,
    is_group BOOLEAN NOT NULL,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE tb_interlocutor_chat (
    chat_id UUID NOT NULL,
    interlocutor_id UUID NOT NULL,
    joined_at TIMESTAMPTZ NOT NULL
);

ALTER TABLE tb_interlocutor_chat
ADD CONSTRAINT pk_interlocutor_chat_id
PRIMARY KEY (interlocutor_id, chat_id);

ALTER TABLE tb_interlocutor_chat
ADD CONSTRAINT fk_interlocutor_chat_interlocutor_id
FOREIGN KEY (interlocutor_id) REFERENCES tb_interlocutor (id);

ALTER TABLE tb_interlocutor_chat
ADD CONSTRAINT fk_interlocutor_chat_chat_id
FOREIGN KEY (chat_id) REFERENCES tb_chat (id);

CREATE TABLE tb_message (
    id UUID PRIMARY KEY,
    content TEXT NOT NULL,
    read BOOLEAN NOT NULL,
    reply_message_id UUID,
    sender_id UUID NOT NULL,
    chat_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);

ALTER TABLE tb_message
ADD CONSTRAINT fk_chat_id
FOREIGN KEY (chat_id) REFERENCES tb_chat (id);

ALTER TABLE tb_message
ADD CONSTRAINT fk_message_message_id
FOREIGN KEY (reply_message_id) REFERENCES tb_message (id);

ALTER TABLE tb_message
ADD CONSTRAINT fk_message_interlocutor_id
FOREIGN KEY (sender_id) REFERENCES tb_interlocutor (id);