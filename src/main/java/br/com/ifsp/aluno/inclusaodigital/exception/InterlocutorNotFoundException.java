package br.com.ifsp.aluno.inclusaodigital.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InterlocutorNotFoundException extends CommonException {

    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Interlocutor Not Found");
        pb.setDetail("interlocutor not found in the database");

        return pb;
    }
}
