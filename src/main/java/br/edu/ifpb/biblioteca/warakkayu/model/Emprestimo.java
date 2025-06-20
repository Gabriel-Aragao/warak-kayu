package br.edu.ifpb.biblioteca.warakkayu.model;

import java.time.LocalDate;

public class Emprestimo {
    private long id;
    private Usuario usuario;
    private Obra obra;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    
    public Emprestimo(Usuario usuario, Obra obra) {
        this.id = System.currentTimeMillis();
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = obra.calcularDataDevolucao();
    }
    public long getId() {
        return id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Obra getObra() {
        return obra;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }
    
    
}
