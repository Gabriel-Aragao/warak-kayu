package br.edu.ifpb.biblioteca.warakkayu.model;

public class Revista extends Obra{

    public Revista(long codigo, String titulo, String autor, int anoPublicacao, StatusObra statusObra, double valorDaMulta) {
        super(codigo, titulo, autor, anoPublicacao, statusObra, valorDaMulta);
    }

    @Override
    public int getTempoEmprestimo() {
        return 3;
    }
}
