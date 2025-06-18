package br.edu.ifpb.biblioteca.warakkayu.model;

public class Artigo extends Obra{
    @Override
    public int getTempoEmprestimo() {
        return 2;
    }
}
