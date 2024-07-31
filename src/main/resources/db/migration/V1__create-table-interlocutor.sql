CREATE TABLE tb_interlocutor_type (
    id BIGSERIAL PRIMARY KEY,
    description CHARACTER VARYING NOT NULL UNIQUE
);

INSERT INTO tb_interlocutor_type (description) VALUES ('refugee');
INSERT INTO tb_interlocutor_type (description) VALUES ('immigrant');
INSERT INTO tb_interlocutor_type (description) VALUES ('collaborator');

CREATE TABLE tb_interlocutor (
    id UUID PRIMARY KEY,
    name CHARACTER VARYING NOT NULL,
    about_me TEXT,
    email CHARACTER VARYING NOT NULL UNIQUE,
    password CHARACTER VARYING NOT NULL,
    interlocutor_type_id BIGINT,
    date_of_birth DATE NOT NULL,
    current_state CHARACTER VARYING NOT NULL,
    current_city CHARACTER VARYING NOT NULL,
    profile_picture CHARACTER VARYING,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL
);

ALTER TABLE tb_interlocutor
ADD CONSTRAINT fk_interlocutor_type
FOREIGN KEY (interlocutor_type_id) REFERENCES tb_interlocutor_type (id);