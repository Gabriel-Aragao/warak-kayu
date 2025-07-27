package br.edu.ifpb.biblioteca.warakkayu.shared.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.ModificacaoDeSenhaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TelaCadastroSenha;

public class TelaCadastroSenhaController  implements AcoesCadastroSenhaListener {

    private TelaCadastroSenha view;
    private Router router;
    private AuthService authService;
    private String matricula;

    public TelaCadastroSenhaController(
            TelaCadastroSenha view, Router router, AuthService authService, String matricula
        ) 
    {
        this.view = view;
        this.router = router;
        this.authService = authService;
        this.matricula = matricula;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarCadastrar() {
        char[] senhaChar = this.view.getSenha().getPassword();
        char[] confirmarSenhaChar = this.view.getConfirmarSenha().getPassword();
        String senha = new String(senhaChar);
        String confirmarSenha = new String(confirmarSenhaChar);

        if(!senha.equals(confirmarSenha)){
            this.view.exibirErro("Senhas diferentes. Tente novamente");
        } else {
            try {
                boolean confirma = this.view.exibirConfirmacao(
                    "Após criada a senha não pode ser alterada.\nDeseja Prosseguir?"
                );
                if(confirma){
                    authService.cadastrarSenha(this.matricula, senha);
                    this.view.exibirMensagem(
                        "Senha cadastrada com sucesso!\nEfetue login para acessar o sistema."
                    );
                    this.view.dispose();
                    this.router.toTelaLogin();
                } else {
                    this.view.dispose();
                    this.router.toTelaLogin();
                }
            } catch (NaoEncontradoException | PersistenciaException e) {
                this.view.exibirErro("Erro ao cadastrar senha para o usuário: "+this.matricula);
                this.router.toTelaLogin();
            } catch (ModificacaoDeSenhaException e) {
                this.view.exibirErro(e.getMessage());
                this.view.dispose();
                this.router.toTelaLogin();
            }
        }
    }
    
}
