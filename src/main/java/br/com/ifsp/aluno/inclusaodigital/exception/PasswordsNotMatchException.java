package br.com.ifsp.aluno.inclusaodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PasswordsNotMatchException extends CommonException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Passwords Not Matches");
        pb.setDetail("passwords do not match");

        return pb;
    }
}
