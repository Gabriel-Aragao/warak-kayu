package br.edu.ifpb.biblioteca.warakkayu.devolucao.exception;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;

public class DevolucaoNaoEncontrada extends NaoEncontradoException{

    public DevolucaoNaoEncontrada(Throwable cause){
        super("Não foi possível encontrar a devolução com o id associado.", cause);
    }

    public DevolucaoNaoEncontrada(){
        super("Não foi possível encontrar a devolução com o id associado.");
    }

}
