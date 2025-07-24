
package br.edu.ifpb.biblioteca.warakkayu.model;
import java.time.LocalDate;
import java.util.UUID;
import br.edu.ifpb.biblioteca.warakkayu.dao.Persistivel;

public class Devolucao {
    private UUID id;
    private LocalDate dataDevolucao;
    private Emprestimo emprestimo;

    public Devolucao(Emprestimo emprestimo) {
        this.dataDevolucao = LocalDate.now();
        this.id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }
    
}