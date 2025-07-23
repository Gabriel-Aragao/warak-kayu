package br.edu.ifpb.biblioteca.warakkayu.controller;

// Esta interface é o contrato de comunicação entre o painel de ações e quem o utiliza.
public interface AcoesDoPainelListener {
    public void aoClicarCriar();
    public void aoClicarAtualizar();
    public void aoClicarRemover();
    public void aoClicarEmprestar();
    public void aoClicarDevolver();
    public void aplicarPermissoes();
}