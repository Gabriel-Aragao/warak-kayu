package br.edu.ifpb.biblioteca.warakkayu.controller;

import java.util.List;
import javax.swing.JOptionPane;
import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.service.UsuarioService;
import br.edu.ifpb.biblioteca.warakkayu.view.GerenciamentoDeUsuarios;

public class GerenciamentoDeUsuariosController implements AcoesDoPainelListener {

    private final GerenciamentoDeUsuarios view;
    private final UsuarioService usuarioService;
    private final AuthService authService;
    private final Router router;

    public GerenciamentoDeUsuariosController(
            GerenciamentoDeUsuarios view, 
            UsuarioService usuarioService, 
            Router router, 
            AuthService authService
        ) 
    {
        this.view = view;
        this.usuarioService = usuarioService;
        this.router = router;
        this.authService = authService;
        this.view.getPainelAcoes().setListener(this);
        this.aplicarPermissoes();
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
            router.toCadastroDeUsuario(this.view, usuarioSelecionado);
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

    // --- Métodos não aplicáveis ao Gerenciamento de Usuários ---

    @Override
    public void aoClicarEmprestar() {
        // Esta ação não se aplica a esta tela.
    }

    @Override
    public void aoClicarDevolver() {
        // Esta ação não se aplica a esta tela.
    }

    // --- Lógica de Permissões ---

    @Override
    public void aplicarPermissoes() {
        TipoUsuario tipoUsuario = this.authService.getTipoUsuario();
        boolean podeGerenciarUsuarios = (tipoUsuario == TipoUsuario.ADMIN);

        // Apenas o ADMIN pode criar, atualizar ou remover usuários.
        this.view.getPainelAcoes().getBotaoCriar().setEnabled(podeGerenciarUsuarios);
        this.view.getPainelAcoes().getBotaoAtualizar().setEnabled(podeGerenciarUsuarios);
        this.view.getPainelAcoes().getBotaoRemover().setEnabled(podeGerenciarUsuarios);

        // Botões de Empréstimo e Devolução não são usados nesta tela.
        this.view.getPainelAcoes().getBotaoEmprestar().setEnabled(false);
        this.view.getPainelAcoes().getBotaoDevolver().setEnabled(false);
    }
}