package com.petcare.infra;

public class ProprietarioNotFoundException extends RuntimeException {

    public ProprietarioNotFoundException(String mensagem) {
        super(mensagem);
    }
}