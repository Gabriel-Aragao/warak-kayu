package br.edu.ifpb.biblioteca.warakkayu.pagamento.exception;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;

public class PagamentoNaoEncontradoException  extends NaoEncontradoException {

    public PagamentoNaoEncontradoException(Throwable cause){
        super("Pagamento não registrado.", cause);
    }

    public PagamentoNaoEncontradoException(){
        super("Pagamento não registrado.");
    }
}
