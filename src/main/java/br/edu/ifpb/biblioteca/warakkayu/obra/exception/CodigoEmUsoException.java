package br.edu.ifpb.biblioteca.warakkayu.obra.exception;

public class CodigoEmUsoException extends Exception {
    public CodigoEmUsoException(Throwable cause){
        super("O código utilizado já está atribuido a outra obra.", cause);
    }

    public CodigoEmUsoException(){
        super("O código utilizado já está atribuido a outra obra.");
    }
}
