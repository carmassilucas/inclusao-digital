CREATE TABLE tb_interlocutor (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    about_me TEXT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    interlocutor_type_id BIGINT,
    date_of_birth DATE NOT NULL,
    current_state VARCHAR(255) NOT NULL,
    current_city VARCHAR(255) NOT NULL,
    profile_picture TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT current_timestamp
);

CREATE TABLE tb_interlocutor_type (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL UNIQUE
);

ALTER TABLE tb_interlocutor
ADD CONSTRAINT fk_interlocutor_type
FOREIGN KEY (interlocutor_type_id) REFERENCES tb_interlocutor_type(id);