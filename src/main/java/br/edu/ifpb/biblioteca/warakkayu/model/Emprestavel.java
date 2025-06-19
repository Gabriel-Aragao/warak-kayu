package br.edu.ifpb.biblioteca.warakkayu.model;
import java.time.LocalDate;

public interface Emprestavel {
    public void emprestar();
    public void devolver();
    public boolean isDisponivel();
    public LocalDate calcularDataDevolucao(); 
}
