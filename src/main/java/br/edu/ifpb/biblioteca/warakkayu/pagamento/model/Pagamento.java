package br.edu.ifpb.biblioteca.warakkayu.pagamento.model;

import java.time.LocalDate;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class Pagamento {

    private UUID id;
    private double valor;
    private LocalDate data;
    private MetodoPagamento metodoPagamento;
    private Usuario pagante;
    private Usuario recebedor;
    private Emprestimo emprestimo;

    public Pagamento(
        double valor, LocalDate data, MetodoPagamento metodoPagamento, Usuario pagante, Usuario recebedor,
        Emprestimo emprestimo
        ) 
        {
            this.id = UUID.randomUUID();
            this.valor = valor;
            this.data = data;
            this.metodoPagamento = metodoPagamento;
            this.pagante = pagante;
            this.recebedor = recebedor;
            this.emprestimo = emprestimo;
    }

     public Pagamento(
        UUID id, double valor, LocalDate data, MetodoPagamento metodoPagamento, Usuario pagante, 
        Usuario recebedor, Emprestimo emprestimo
        ) 
    {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.metodoPagamento = metodoPagamento;
        this.pagante = pagante;
        this.recebedor = recebedor;
        this.emprestimo = emprestimo;
    }

    public UUID getId() {
        return id;
    }
    public double getValor() {
        return valor;
    }
    public LocalDate getData() {
        return data;
    }
    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }
    public Usuario getPagante() {
        return pagante;
    }
    public Usuario getRecebedor() {
        return recebedor;
    }
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }  
    
}
