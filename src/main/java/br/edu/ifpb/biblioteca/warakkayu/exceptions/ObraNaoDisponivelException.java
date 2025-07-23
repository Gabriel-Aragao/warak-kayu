package br.edu.ifpb.biblioteca.warakkayu.exceptions;

public class ObraNaoDisponivelException extends Exception {
        
    public ObraNaoDisponivelException(Throwable cause){
        super("A obra escolhida não está disponível.", cause);
    }

    public ObraNaoDisponivelException(){
        super("A obra escolhida não está disponível.");
    }
}
