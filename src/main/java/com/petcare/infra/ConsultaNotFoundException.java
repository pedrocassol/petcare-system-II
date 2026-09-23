package com.petcare.infra;

public class ConsultaNotFoundException extends RuntimeException {

    public ConsultaNotFoundException(String mensagem) {
        super(mensagem);
    }
}