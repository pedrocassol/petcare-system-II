package com.petcare.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ProprietarioNotFoundException.class)
    public ResponseEntity<String> tratarProprietarioNaoEncontrado(
            ProprietarioNotFoundException exception) {

        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<String> tratarPetNaoEncontrado(
            PetNotFoundException exception) {

        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity<String> tratarConsultaNaoEncontrada(
            ConsultaNotFoundException exception) {

        return ResponseEntity.status(404).body(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> tratarDadosInvalidos(
            MethodArgumentNotValidException exception) {

        String campo = exception.getBindingResult()
                .getFieldErrors().get(0).getField();

        String mensagem = exception.getBindingResult()
                .getFieldErrors().get(0).getDefaultMessage();

        return ResponseEntity.badRequest()
                .body("Campo '" + campo + "': " + mensagem);
    }
}