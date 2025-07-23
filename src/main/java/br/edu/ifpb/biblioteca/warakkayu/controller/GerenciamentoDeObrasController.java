package br.edu.ifpb.biblioteca.warakkayu.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.view.GerenciamentoDeObras;

public class GerenciamentoDeObrasController  implements AcoesDoPainelListener {

    private GerenciamentoDeObras view;
    private ObraService obraService;
    private AuthService authService;
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
        this.authService = authService;
        this.view.getPainelAcoes().setListener(this);
        this.aplicarPermissoes();
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

    @Override
    public void aplicarPermissoes() {
        TipoUsuario tipoUsuario = this.authService.getTipoUsuario();
        switch (tipoUsuario) {
            case ADMIN:
                this.view.getPainelAcoes().getBotaoCriar().setEnabled(true);
                this.view.getPainelAcoes().getBotaoAtualizar().setEnabled(true);
                this.view.getPainelAcoes().getBotaoRemover().setEnabled(true);
                this.view.getPainelAcoes().getBotaoEmprestar().setEnabled(false);
                this.view.getPainelAcoes().getBotaoDevolver().setEnabled(false);
                break;
            case BIBLIOTECARIO:
                this.view.getPainelAcoes().getBotaoCriar().setEnabled(false);
                this.view.getPainelAcoes().getBotaoAtualizar().setEnabled(false);
                this.view.getPainelAcoes().getBotaoRemover().setEnabled(false);
                this.view.getPainelAcoes().getBotaoEmprestar().setEnabled(true);
                this.view.getPainelAcoes().getBotaoDevolver().setEnabled(true);
                break;
            case ESTAGIARIO:
            default:
                this.view.getPainelAcoes().getBotaoCriar().setEnabled(false);
                this.view.getPainelAcoes().getBotaoAtualizar().setEnabled(false);
                this.view.getPainelAcoes().getBotaoRemover().setEnabled(false);
                this.view.getPainelAcoes().getBotaoEmprestar().setEnabled(false); 
                this.view.getPainelAcoes().getBotaoDevolver().setEnabled(true);
                break;
        }
    }

}
