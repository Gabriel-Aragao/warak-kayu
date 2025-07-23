package br.edu.ifpb.biblioteca.warakkayu.exceptions;

public class EmprestimoNaoEncontradoException extends Exception {

    public EmprestimoNaoEncontradoException(Throwable cause){
        super("Empréstimo não registrado.", cause);
    }

    public EmprestimoNaoEncontradoException(){
        super("Empréstimo não registrado.");
    }

}
