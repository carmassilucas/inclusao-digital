package br.com.ifsp.aluno.inclusaodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class EmailAlreadyExistsException extends CommonException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Email Alreay Exists.");
        pb.setDetail("email address is already registered in the system.");

        return pb;
    }
}
