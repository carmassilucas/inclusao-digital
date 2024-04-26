package br.com.ifsp.aluno.inclusaodigital.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException() {
        super("Endereço de e-mail já cadastrado no sistema");
    }
}
