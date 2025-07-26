package br.edu.ifpb.biblioteca.warakkayu.obra.model;

public class Livro extends Obra {

    public Livro(long codigo, String titulo, String autor, int anoPublicacao, double valorDaMulta) {
        super(TipoObra.LIVRO, codigo, titulo, autor, anoPublicacao, valorDaMulta);
    }

    @Override
    public int  getTempoEmprestimo() {
        return 7;
    }
}
