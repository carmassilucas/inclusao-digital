package br.com.ifsp.aluno.inclusaodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ChatNotFoundException extends CommonException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Chat Not Found");
        pb.setDetail("chat not found in the database");

        return pb;
    }
}
