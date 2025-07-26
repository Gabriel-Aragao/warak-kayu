package br.edu.ifpb.biblioteca.warakkayu.obra.model;

public class Artigo extends Obra{

    public Artigo(long codigo, String titulo, String autor, int anoPublicacao, double valorDaMulta) {
        super(TipoObra.ARTIGO, codigo, titulo, autor, anoPublicacao, valorDaMulta);
    }
    @Override
    public int getTempoEmprestimo() {
        return 2;
    }
}
