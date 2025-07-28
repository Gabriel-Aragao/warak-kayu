package br.edu.ifpb.biblioteca.warakkayu.devolucao.controller;

import java.util.List; 
import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.devolucao.exception.DevolucaoNaoEncontrada;
import br.edu.ifpb.biblioteca.warakkayu.devolucao.view.TelaDevolucao;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;

public class DevolucaoController implements DevolucaoListener {
    private TelaDevolucao view;
    private EmprestimoService emprestimoService;
    private Router router;

    public DevolucaoController(TelaDevolucao view, EmprestimoService emprestimoService, Router router) {
        this.view = view;
        this.emprestimoService = emprestimoService;
        this.router = router;
        
        this.view.setListener(this);
        
        this.carregarDados();
    }


    private void carregarDados() {
        List<Emprestimo> emprestimos = emprestimoService.listEmprestimosEmCurso();
        view.getDevolucaoTableModel().setEmprestimos(emprestimos);
    }
    

    @Override
    public void aoClicarDevolver() {

        Emprestimo emprestimoSelecionado = view.getDevolucaoSelecionada();
        
        if (emprestimoSelecionado == null) {
            view.exibirAviso("Selecione um empréstimo na tabela para realizar a Devolução.");
            return; 
        }
        try {
            emprestimoService.realizarDevolucao(emprestimoSelecionado);

            this.view.exibirAviso("Devolução realizada com sucesso!");
            this.carregarDados();

        } catch (DevolucaoNaoEncontrada e) {
            this.view.exibirAviso("Devolução não encontrada: " + e.getMessage());
        } catch (Exception e) { 
            this.view.exibirErro("Ocorreu um erro ao realizar a devolução: " + e.getMessage());
        }
    }
}