package br.edu.ifpb.biblioteca.warakkayu.model;

public class Revista extends Obra{

    public Revista(long codigo, String titulo, String autor, int anoPublicacao, double valorDaMulta) {
        super(TipoObra.REVISTA, codigo, titulo, autor, anoPublicacao, valorDaMulta);
    }

    @Override
    public int getTempoEmprestimo() {
        return 3;
    }
}
