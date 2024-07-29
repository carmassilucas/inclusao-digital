package br.com.ifsp.aluno.inclusaodigital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ProblemDetail handleCommonException(CommonException e) {
        return e.toProblemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErros = e.getFieldErrors().stream().map(f ->
                new InvalidParam(f.getField(), f.getDefaultMessage())
        ).toList();

        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Parâmetros Inválidos");
        pb.setProperty("Os parâmetros da sua solicitação não são validados", fieldErros);

        return pb;
    }

    private record InvalidParam(String name, String reason){}
}
