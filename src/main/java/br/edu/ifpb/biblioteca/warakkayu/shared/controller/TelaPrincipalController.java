package br.edu.ifpb.biblioteca.warakkayu.shared.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router; 
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TelaPrincipal;

import javax.swing.JOptionPane;

public class TelaPrincipalController implements AcoesTelaPrincipalListener {
    
    private TelaPrincipal view;
    private Router router;
    private AuthService authService;

    public TelaPrincipalController(TelaPrincipal view, Router router, AuthService authService) {
        this.view = view;
        this.router = router;
        this.authService = authService;
        this.view.setListener(this);
        atribuirPermissoes();
    }
    
    private void atribuirPermissoes(){
        TipoUsuario tipoUsuario = authService.getTipoUsuario();
        switch (tipoUsuario) {
            case ADMIN:
                this.view.getBotaoUsuarios().setEnabled(true);
                this.view.getBotaoObras().setEnabled(true);
                this.view.getBotaoEmprestimos().setEnabled(false);
                this.view.getBotaoDevolucoes().setEnabled(false);
                this.view.getBotaoRelatorios().setEnabled(false);
                break;
            case BIBLIOTECARIO:
                this.view.getBotaoUsuarios().setEnabled(false);
                this.view.getBotaoObras().setEnabled(false);
                this.view.getBotaoEmprestimos().setEnabled(true);
                this.view.getBotaoDevolucoes().setEnabled(true);
                this.view.getBotaoRelatorios().setEnabled(true);
                break;
            case ESTAGIARIO:
                this.view.getBotaoUsuarios().setEnabled(false);
                this.view.getBotaoObras().setEnabled(false);
                this.view.getBotaoEmprestimos().setEnabled(false);
                this.view.getBotaoDevolucoes().setEnabled(true);
                this.view.getBotaoRelatorios().setEnabled(false);
                break;
            default:
                this.view.getBotaoUsuarios().setEnabled(false);
                this.view.getBotaoObras().setEnabled(false);
                this.view.getBotaoEmprestimos().setEnabled(false);
                this.view.getBotaoDevolucoes().setEnabled(false);
                this.view.getBotaoRelatorios().setEnabled(false);
                break;
        }

    }

    @Override
    public void aoClicarUsuarios() {
        this.router.toGerenciamentoUsuarios(this.view);
    }

    @Override
    public void aoClicarObras() {
        this.router.toGerenciamentoObras(this.view);
    }

    @Override
    public void aoClicarEmprestimos() {
        this.router.toEmprestimo(this.view);
    }

    @Override
    public void aoClicarDevolucoes() {
        this.view.exibirErro("Funcionalidade Não implementada!");
    }

    @Override
    public void aoClicarRelatorios() {
        this.view.exibirErro("Funcionalidade Não implementada!");
    }
}