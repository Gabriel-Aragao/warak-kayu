package br.edu.ifpb.biblioteca.warakkayu.exceptions;

public class ObraNaoEncontradaException extends Exception{

    public ObraNaoEncontradaException(Throwable cause){
        super("Não foi possível encontrar a obra com o id associado.", cause);
    }

    public ObraNaoEncontradaException(){
        super("Não foi possível encontrar a obra com o id associado.");
    }

}
