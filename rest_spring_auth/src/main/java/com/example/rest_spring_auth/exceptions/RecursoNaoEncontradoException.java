package com.example.rest_spring_auth.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String mensagem){
        super(mensagem);
    }
    
}
