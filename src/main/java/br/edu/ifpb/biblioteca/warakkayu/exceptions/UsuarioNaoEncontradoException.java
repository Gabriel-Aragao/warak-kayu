package br.edu.ifpb.biblioteca.warakkayu.exceptions;

public class UsuarioNaoEncontradoException extends Exception{
    
    public UsuarioNaoEncontradoException(Throwable cause){
        super("Não foi possível encontrar o usuário com o id associado.", cause);
    }

    public UsuarioNaoEncontradoException(){
        super("Não foi possível encontrar o usuário com o id associado.");
    }

}
