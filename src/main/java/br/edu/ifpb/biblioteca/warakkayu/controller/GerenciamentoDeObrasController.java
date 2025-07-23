package br.edu.ifpb.biblioteca.warakkayu.controller;

import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.view.GerenciamentoDeObras;
import br.edu.ifpb.biblioteca.warakkayu.view.Router;

public class GerenciamentoDeObrasController  implements AcoesDoPainelListener {

    private GerenciamentoDeObras view;
    private ObraService obraService;
    private Router router;

    public GerenciamentoDeObrasController(
            GerenciamentoDeObras view, 
            ObraService obraService, 
            Router router, 
            AuthService authService
        ) 
    {
        this.view = view;
        this.obraService = obraService;
        this.router = router;
        
        this.view.getPainelAcoes().setListener(this);
        this.view.getPainelAcoes().aplicarPermissoes(authService.getTipoUsuario());
    }
    
    public void carregarDados() {
        try {
            view.getObrasTableModel().setObras(obraService.list());
        } catch (Exception e) {
            view.exibirErro("Falha ao carregar dados das obras: " + e.getMessage());
        }
    }

    @Override
    public void aoClicarCriar() {
        Obra obraSelecionada = view.getObraSelecionada();
        router.toCadastroDeObra(this.view, obraSelecionada);
    }

    @Override
    public void aoClicarAtualizar() {
        Obra obraSelecionada = view.getObraSelecionada();
        if (obraSelecionada != null) {
            router.toCadastroDeObra(this.view, obraSelecionada);
        } else {
            view.exibirAviso("Selecione uma obra na tabela para atualizar.");
        }
    }

    @Override
    public void aoClicarRemover() {
        Obra obraSelecionada = view.getObraSelecionada();
        if (obraSelecionada != null) {
            boolean confirmado = view.exibirConfirmacao("Deseja realmente remover a obra '" + obraSelecionada.getTitulo() + "'?");
            if (confirmado) {
                try {
                    obraService.delete(obraSelecionada.getId());
                    view.exibirAviso("Obra removida com sucesso!");
                    carregarDados();
                } catch (ObraNaoEncontradaException | PersistenciaException e) {
                    view.exibirErro(e.getMessage());
                }
            }
        } else {
            view.exibirAviso("Selecione uma obra na tabela para remover.");
        }
    }

    @Override
    public void aoClicarEmprestar() {
        Obra obraSelecionada = view.getObraSelecionada();
        if (obraSelecionada != null) {
        } else {
            view.exibirAviso("Selecione uma obra para realizar o empréstimo.");
        }
    }

    @Override
    public void aoClicarDevolver() {
        Obra obraSelecionada = view.getObraSelecionada();
        if (obraSelecionada != null) {
        } else {
            view.exibirAviso("Selecione uma obra para realizar a Devolução.");
        }
    }
}