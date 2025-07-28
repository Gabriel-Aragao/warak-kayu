package br.edu.ifpb.biblioteca.warakkayu.relatorio.controller;

import java.io.IOException;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.service.RelatorioService;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.view.TelaRelatorio;

public class TelaRelatorioController  implements AcoesRelatoriosListener {
    
    private TelaRelatorio view;
    private Router router;
    private EmprestimoService emprestimoService;
    private RelatorioService relatorioService;

    public TelaRelatorioController(
            TelaRelatorio view, Router router, EmprestimoService emprestimoService, 
            RelatorioService relatorioService
        ) 
    {
        this.view = view;
        this.router = router;
        this.emprestimoService = emprestimoService;
        this.relatorioService = relatorioService;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarUsuarios() {
        try {
            this.relatorioService.gerarRelatorioUsuariosComMaisAtrasos();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void aoClicarObras() {
        try {
            this.relatorioService.gerarRelatorioObrasMaisEmprestadas();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   

    @Override
    public void aoClicarEmprestimos() {
        try {
            this.relatorioService.gerarRelatorioEmprestimosDoMes();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
