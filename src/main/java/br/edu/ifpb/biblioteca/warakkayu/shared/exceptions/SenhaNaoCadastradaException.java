package br.edu.ifpb.biblioteca.warakkayu.shared.exceptions;

public class SenhaNaoCadastradaException extends Exception {

    public SenhaNaoCadastradaException(Throwable cause){
        super("Usuário com senha não cadastrada.", cause);
    }

    public SenhaNaoCadastradaException(){
        super("Usuário com senha não cadastrada.");
    }
}
