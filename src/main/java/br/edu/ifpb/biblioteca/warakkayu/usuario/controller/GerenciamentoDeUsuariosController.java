package br.edu.ifpb.biblioteca.warakkayu.usuario.controller;

import java.util.List;
import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesDoRodapeListener;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.view.GerenciamentoDeUsuarios;

public class GerenciamentoDeUsuariosController implements AcoesDoRodapeListener {

    private final GerenciamentoDeUsuarios view;
    private final UsuarioService usuarioService;
    private final Router router;

    public GerenciamentoDeUsuariosController(
            GerenciamentoDeUsuarios view, 
            UsuarioService usuarioService, 
            Router router
        ) 
    {
        this.view = view;
        this.usuarioService = usuarioService;
        this.router = router;
        this.view.getPainelAcoes().setListener(this);
    }
    
    public void carregarDados() {
        try {
            List<Usuario> usuarios = this.usuarioService.list();
            view.getUsuariosTableModel().setUsuarios(usuarios);
        } catch (Exception e) {
            view.exibirErro("Falha ao carregar dados dos usuários: " + e.getMessage());
        }
    }

    @Override
    public void aoClicarCriar() {
        // Para criar um novo usuário, navegamos para a tela de cadastro passando 'null'
        router.toCadastroDeUsuario(this.view, null);
    }

    @Override
    public void aoClicarAtualizar() {
        Usuario usuarioSelecionado = view.getUsuarioSelecionado();
    
        if (usuarioSelecionado != null) {
            if(usuarioSelecionado.getTipoUsuario() == TipoUsuario.REMOVIDO) {
                view.exibirErro("Não é possivel modificar um usuário removido.");
            } else {
                router.toCadastroDeUsuario(this.view, usuarioSelecionado);
            }
        } else {
            view.exibirAviso("Selecione um usuário na tabela para atualizar.");
        }
    }

    @Override
    public void aoClicarRemover() {
        Usuario usuarioSelecionado = view.getUsuarioSelecionado();
        if (usuarioSelecionado != null) {
            boolean confirmado = view.exibirConfirmacao("Deseja realmente remover o usuário '" + usuarioSelecionado.getNome() + "'?");
            if (confirmado) {
                try {
                    usuarioService.delete(usuarioSelecionado.getId());
                    view.exibirAviso("Usuário removido com sucesso!");
                    carregarDados(); // Atualiza a tabela
                } catch (UsuarioNaoEncontradoException | PersistenciaException e) {
                    view.exibirErro(e.getMessage());
                }
            }
        } else {
            view.exibirAviso("Selecione um usuário na tabela para remover.");
        }
    }

}