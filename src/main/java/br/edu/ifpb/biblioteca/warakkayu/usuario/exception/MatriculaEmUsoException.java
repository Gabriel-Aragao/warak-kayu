package br.edu.ifpb.biblioteca.warakkayu.usuario.exception;

public class MatriculaEmUsoException extends Exception {
    public MatriculaEmUsoException(Throwable cause){
        super("A matrícula utilizada já está atribuida a outro usuário.", cause);
    }

    public MatriculaEmUsoException(){
        super("A matrícula utilizada já está atribuida a outro usuário.");
    }
}
