package br.edu.ifpb.biblioteca.warakkayu.model;

public class Artigo extends Obra{

    public Artigo(long codigo, String titulo, String autor, int anoPublicacao, StatusObra statusObra, double valorDaMulta) {
        super(codigo, titulo, autor, anoPublicacao, statusObra, valorDaMulta);
    }
    @Override
    public int getTempoEmprestimo() {
        return 2;
    }
}
