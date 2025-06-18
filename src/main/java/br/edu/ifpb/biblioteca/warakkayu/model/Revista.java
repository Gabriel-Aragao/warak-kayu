package br.edu.ifpb.biblioteca.warakkayu.model;

public class Revista extends Obra{
    @Override
    public int getTempoEmprestimo() {
        return 3;
    }
}
