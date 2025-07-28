package br.edu.ifpb.biblioteca.warakkayu.emprestimo.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.shared.util.FormatadoresDeData;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;


public class Emprestimo {
    private UUID id;
    private Usuario usuario;
    private Obra obra;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;
    private StatusEmprestimo statusEmprestimo;
    
    public Emprestimo(Usuario usuario, Obra obra) {
        this.id = UUID.randomUUID();
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = obra.calcularDataDevolucao();
        this.statusEmprestimo = StatusEmprestimo.EM_CURSO;
    }

    public Emprestimo(
            UUID id, Usuario usuario, Obra obra, LocalDate dataEmprestimo,
            LocalDate dataPrevistaDevolucao, LocalDate dataRealDevolucao, 
            StatusEmprestimo statusEmprestimo
        ) 
    {
        this.id = id;
        this.usuario = usuario;
        this.obra = obra;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataRealDevolucao = dataRealDevolucao;
        this.statusEmprestimo = statusEmprestimo;
    }

    public UUID getId() {
        return id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Obra getObra() {
        return obra;
    }
    public LocalDate getDataEmprestimo() {
        return this.dataEmprestimo;
    }
    public LocalDate getDataPrevistaDevolucao() {
        return this.dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao() {
        return this.dataRealDevolucao;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return this.statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }

    public void registrarDevolucao() {
        this.dataRealDevolucao = LocalDate.now();
        if(this.dataRealDevolucao.isAfter(this.dataPrevistaDevolucao)) {
            this.statusEmprestimo = StatusEmprestimo.CONCLUIDO_COM_ATRASO;
        } else {
            this.statusEmprestimo = StatusEmprestimo.CONCLUIDO;
        }
    }
    public double calcularMulta() {

        if (dataPrevistaDevolucao.isBefore(LocalDate.now())) {
            int dias = (int) ChronoUnit.DAYS.between(dataPrevistaDevolucao, LocalDate.now());
            double multa = dias * obra.getValorDaMulta();
            return multa;
        }
        return 0.00;
    }
    
    
}
