package br.edu.ifpb.biblioteca.warakkayu.model;

public class Livro extends Obra {
    @Override
    public int  getTempoEmprestimo() {
        return 7;
    }
}
