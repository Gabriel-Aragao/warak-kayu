package br.edu.ifpb.biblioteca.warakkayu.usuario.controller;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesDeCadastroListener;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.view.CadastroDeUsuario;

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
        TipoUsuario tipoUsuario = (TipoUsuario) view.getTipo().getSelectedItem();

        try {
            usuarioService.save(usuario, matricula, nome, email, telefone, tipoUsuario);
            this.view.exibirMensagem("Usuário salvo com sucesso!");
        } catch (PersistenciaException e) {
            this.view.exibirErro("Erro ao salvar o Usuário! \nOperação Cancelada.");
        } catch (UsuarioNaoEncontradoException e) {
            this.view.exibirErro("Usuário Não encontrado! \nOperação Cancelada.");
        }
        
        this.router.toGerenciamentoUsuarios(view);;
    }
}