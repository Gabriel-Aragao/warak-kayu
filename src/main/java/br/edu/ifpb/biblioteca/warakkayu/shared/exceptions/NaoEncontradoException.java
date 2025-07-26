package br.edu.ifpb.biblioteca.warakkayu.shared.exceptions;

public class NaoEncontradoException extends Exception {

    public NaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NaoEncontradoException(String message){
        super(message);
    }
    
}