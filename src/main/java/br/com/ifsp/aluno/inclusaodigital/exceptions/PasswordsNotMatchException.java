package br.com.ifsp.aluno.inclusaodigital.exceptions;

public class PasswordsNotMatchException extends RuntimeException {
    public PasswordsNotMatchException() {
        super("Senhas não coincidem");
    }
}
