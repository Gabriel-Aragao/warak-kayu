package br.edu.ifpb.biblioteca.warakkayu.obra.exception;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;

public class ObraNaoEncontradaException extends NaoEncontradoException{

    public ObraNaoEncontradaException(Throwable cause){
        super("Não foi possível encontrar a obra com o id associado.", cause);
    }

    public ObraNaoEncontradaException(){
        super("Não foi possível encontrar a obra com o id associado.");
    }

}
