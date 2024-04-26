package br.com.ifsp.aluno.inclusaodigital.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {
        final var fieldErros = new ArrayList<ExceptionHandlerResponseDto>();

        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(fieldError ->
            fieldErros.add(new ExceptionHandlerResponseDto(
                    fieldError.getDefaultMessage(),
                    fieldError.getField()
            ))
        );

        return ResponseEntity.badRequest().body(fieldErros);
    }
}
