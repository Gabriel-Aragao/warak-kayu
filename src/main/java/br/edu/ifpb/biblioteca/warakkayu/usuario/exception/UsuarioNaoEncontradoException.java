package br.edu.ifpb.biblioteca.warakkayu.usuario.exception;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;

public class UsuarioNaoEncontradoException extends NaoEncontradoException{
    
    public UsuarioNaoEncontradoException(Throwable cause){
        super("Não foi possível encontrar o usuário com o id associado.", cause);
    }

    public UsuarioNaoEncontradoException(){
        super("Não foi possível encontrar o usuário com o id associado.");
    }
}
