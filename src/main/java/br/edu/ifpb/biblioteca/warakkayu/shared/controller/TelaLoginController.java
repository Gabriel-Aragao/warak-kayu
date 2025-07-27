package br.edu.ifpb.biblioteca.warakkayu.shared.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.AuthenticacaoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.SenhaNaoCadastradaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TelaLogin;

public class TelaLoginController implements AcoesLoginListener {

    private TelaLogin view;
    private Router router;
    private AuthService authService;

    public TelaLoginController(TelaLogin view, Router router, AuthService authService) {
        this.view = view;
        this.router = router;
        this.authService = authService;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarLogin() {
        String matricula = this.view.getMatricula().getTextField().getText();
        char[] senhaChar = this.view.getSenha().getPassword();
        String senha = new String(senhaChar);
        try {
            authService.autenticar(matricula, senha);
            this.router.toTelaPrincipal(this.view);
        } catch (AuthenticacaoException e) {
            this.view.exibirErro(e.getMessage());
        } catch (SenhaNaoCadastradaException e) {
            this.view.exibirAviso("Redirecionando para o cadastramento de senha.");
            this.router.toCadastroDeSenha(this.view, this.router, this.authService, matricula);
        }
    }
    
}
