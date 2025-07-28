package br.edu.ifpb.biblioteca.warakkayu.relatorio.controller;

import java.io.IOException;

import br.edu.ifpb.biblioteca.warakkayu.relatorio.service.RelatorioService;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.view.TelaRelatorio;

public class TelaRelatorioController  implements AcoesRelatoriosListener {
    
    private TelaRelatorio view;
    private RelatorioService relatorioService;

    public TelaRelatorioController(TelaRelatorio view,RelatorioService relatorioService) {
        this.view = view;
        this.relatorioService = relatorioService;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarUsuarios() {
        try {
            this.relatorioService.gerarRelatorioUsuariosComMaisAtrasos();
            this.view.exibirMensagem("Relatório Gerado com sucesso no diretorio de relatórios.");
        } catch (IOException e) {
            this.view.exibirErro("Erro ao criar o relatório!");
        }
    }

    @Override
    public void aoClicarObras() {
        try {
            this.relatorioService.gerarRelatorioObrasMaisEmprestadas();
            this.view.exibirMensagem("Relatório Gerado com sucesso no diretorio de relatórios.");
        } catch (IOException e) {
            this.view.exibirErro("Erro ao criar o relatório!");
        }
    }   

    @Override
    public void aoClicarEmprestimos() {
        try {
            this.relatorioService.gerarRelatorioEmprestimosDoMes();
            this.view.exibirMensagem("Relatório Gerado com sucesso no diretorio de relatórios.");
        } catch (IOException e) {
            this.view.exibirErro("Erro ao criar o relatório!");
        }
    }

    @Override
    public void aoClicarPagamentos() {
        try {
            this.relatorioService.gerarRelatorioPagamentosDoMes();
            this.view.exibirMensagem("Relatório Gerado com sucesso no diretorio de relatórios.");
        } catch (IOException e) {
            this.view.exibirErro("Erro ao criar o relatório!");
        }
    }
}
