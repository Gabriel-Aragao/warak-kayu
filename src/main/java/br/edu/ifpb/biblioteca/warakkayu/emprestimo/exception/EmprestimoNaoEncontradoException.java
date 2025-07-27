package br.edu.ifpb.biblioteca.warakkayu.emprestimo.exception;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;

public class EmprestimoNaoEncontradoException extends NaoEncontradoException {

    public EmprestimoNaoEncontradoException(Throwable cause){
        super("Empréstimo não registrado.", cause);
    }

    public EmprestimoNaoEncontradoException(){
        super("Empréstimo não registrado.");
    }
}
