package br.com.ifsp.aluno.inclusaodigital.exceptions;

public class InterlocutorNotFoundException extends RuntimeException {
    public InterlocutorNotFoundException() {
        super("Usuário ou senha incorreto");
    }

}
