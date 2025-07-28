package br.edu.ifpb.biblioteca.warakkayu.emprestimo.controller;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.view.RealizacaoEmprestimo;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoDisponivelException;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoAptoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class RealizacaoEmprestimoController implements EmprestimoListener{

    private RealizacaoEmprestimo view;
    private EmprestimoService emprestimoService;

    public RealizacaoEmprestimoController(
                RealizacaoEmprestimo view, 
                AuthService authService, 
                EmprestimoService emprestimoService
        )
    {
        this.view = view;
        this.emprestimoService = emprestimoService;
        this.view.setListener(this);
    }

    public void carregarDados() {
        view.getObrasTableModel().setObras(emprestimoService.listObrasDisponiveis());
        view.getUsuariosTableModel().setUsuarios(emprestimoService.listLeitores());
    }

    @Override
    public void aoClicarEmprestar() {
        Obra obraSelecionada = view.getObraSelecionada();
        Usuario usuarioSelecionado = view.getUsuarioSelecionado();
        if (obraSelecionada == null) {
            view.exibirAviso("Selecione uma obra na tabela para realizar o empréstimo.");
            return;
        }
        if (usuarioSelecionado == null) {
            view.exibirAviso("Selecione um usuário na tabela para realizar o empréstimo.");
            return;
        }
        try {
            emprestimoService.realizarEmprestimo(usuarioSelecionado, obraSelecionada);
        } catch(
                PersistenciaException | ObraNaoEncontradaException 
                | UsuarioNaoAptoException | ObraNaoDisponivelException e) {
            this.view.exibirErro("Erro ao tentar realizar o empréstimo!\n"+e.getMessage());
        }
        this.view.exibirAviso("Empréstimo realizado com sucesso");
        this.carregarDados();
        
    }
    
}
