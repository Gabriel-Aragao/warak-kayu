package br.edu.ifpb.biblioteca.warakkayu.shared.exceptions;

public class ModificacaoDeSenhaException extends Exception {
    
    public ModificacaoDeSenhaException(Throwable cause){
        super("Não é possivel alterar a senha de um usuário.", cause);
    }

    public ModificacaoDeSenhaException(){
        super("Não é possivel alterar a senha de um usuário.");
    }
}
