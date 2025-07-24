package br.edu.ifpb.biblioteca.warakkayu.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.service.UsuarioService;
import br.edu.ifpb.biblioteca.warakkayu.view.CadastroDeUsuario;

public class CadastroDeUsuarioController implements AcoesDeCadastroListener {
    
    private final CadastroDeUsuario view;
    private final UsuarioService usuarioService;
    private final Router router;

    public CadastroDeUsuarioController(CadastroDeUsuario view, UsuarioService usuarioService, Router router) {
        this.view = view;
        this.usuarioService = usuarioService;
        this.router = router;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarSalvar() {
        Usuario usuario = this.view.getUsuario();
        
        String matricula = this.view.getMatricula().getTextField().getText();
        String nome = this.view.getNome().getTextField().getText();
        String email = this.view.getEmail().getTextField().getText();
        String telefone = this.view.getTelefone().getTextField().getText();
        String senha = this.view.getSenha().getTextField().getText();

        try {
            usuarioService.save(usuario, matricula, nome, email, telefone, senha);
            this.view.exibirMensagem("Usuário salvo com sucesso!");
        } catch (PersistenciaException e) {
            this.view.exibirErro("Erro ao salvar o Usuário! \nOperação Cancelada.");
        } catch (UsuarioNaoEncontradoException e) {
            this.view.exibirErro("Usuário Não encontrado! \nOperação Cancelada.");
        }
        
        this.router.toGerenciamentoUsuarios(view);;
    }
}