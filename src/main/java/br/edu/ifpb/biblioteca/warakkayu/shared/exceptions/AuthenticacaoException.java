package br.edu.ifpb.biblioteca.warakkayu.shared.exceptions;

public class AuthenticacaoException extends Exception {

    public AuthenticacaoException(Throwable cause){
        super("Erro na autenticação: Dados inválidos.", cause);
    }

    public AuthenticacaoException(){
        super("Erro na autenticação: Dados inválidos.");
    }
}
