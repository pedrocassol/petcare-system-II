package com.petcare.infra;

public class PetNotFoundException extends RuntimeException {

    public PetNotFoundException(String mensagem) {
        super(mensagem);
    }
}