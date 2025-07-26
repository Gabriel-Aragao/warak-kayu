package br.edu.ifpb.biblioteca.warakkayu.obra.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.obra.view.GerenciamentoDeObras;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesDoRodapeListener;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;

public class GerenciamentoDeObrasController  implements AcoesDoRodapeListener {

    private GerenciamentoDeObras view;
    private ObraService obraService;
    private Router router;

    public GerenciamentoDeObrasController(
            GerenciamentoDeObras view, 
            ObraService obraService, 
            Router router
        ) 
    {
        this.view = view;
        this.obraService = obraService;
        this.router = router;
        this.view.getPainelAcoes().setListener(this);
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
        router.toCadastroDeObra(this.view, null);
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

}
