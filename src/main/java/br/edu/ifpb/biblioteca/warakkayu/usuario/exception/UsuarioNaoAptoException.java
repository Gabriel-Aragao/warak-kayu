package br.edu.ifpb.biblioteca.warakkayu.usuario.exception;

public class UsuarioNaoAptoException extends Exception {
    public UsuarioNaoAptoException(Throwable cause){
        super("O perfil do usuário não está apto para realizar empréstimos.", cause);
    }

    public UsuarioNaoAptoException(){
        super("O perfil do usuário não está apto para realizar empréstimos.");
    }
}
