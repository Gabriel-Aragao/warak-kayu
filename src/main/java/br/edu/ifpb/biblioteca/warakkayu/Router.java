package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.JFrame;

// Imports de Obra
import br.edu.ifpb.biblioteca.warakkayu.controller.CadastroDeObraController;
import br.edu.ifpb.biblioteca.warakkayu.controller.GerenciamentoDeObrasController;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.view.CadastroDeObra;
import br.edu.ifpb.biblioteca.warakkayu.view.GerenciamentoDeObras;

// --- Imports de Usuário ADICIONADOS ---
import br.edu.ifpb.biblioteca.warakkayu.controller.CadastroDeUsuarioController;
import br.edu.ifpb.biblioteca.warakkayu.controller.GerenciamentoDeUsuariosController;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.service.UsuarioService;
import br.edu.ifpb.biblioteca.warakkayu.view.CadastroDeUsuario;
import br.edu.ifpb.biblioteca.warakkayu.view.GerenciamentoDeUsuarios;

// --- Outros Imports ---
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;


public class Router {

    private ObraService obraService;
    private UsuarioService usuarioService; 
    private AuthService authService;



    public Router(ObraService obraService, UsuarioService usuarioService, AuthService authService) {
        this.obraService = obraService;
        this.usuarioService = usuarioService; 
        this.authService = authService;
    }
    
    private void disposeJanelaPai(JFrame frame) {
        if(frame != null) {
            frame.setVisible(false);
        }
    }



    public void toGerenciamentoObras(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        GerenciamentoDeObras view = new GerenciamentoDeObras(janelaPai, this);
        GerenciamentoDeObrasController controller = new GerenciamentoDeObrasController(view, this.obraService, this, this.authService);
        controller.carregarDados();
        view.setVisible(true);
    }

    public void toCadastroDeObra(JFrame janelaPai, Obra obraSelecionada) {
        disposeJanelaPai(janelaPai);
        CadastroDeObra view = new CadastroDeObra(janelaPai, this, obraSelecionada);
        CadastroDeObraController controller = new CadastroDeObraController(view, obraService, this);
        view.setVisible(true);
    }

    public void toGerenciamentoUsuarios(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        GerenciamentoDeUsuarios view = new GerenciamentoDeUsuarios(janelaPai, this);
        // Supondo que o GerenciamentoDeUsuariosController também precise do authService
        GerenciamentoDeUsuariosController controller = new GerenciamentoDeUsuariosController(view, this.usuarioService, this, this.authService);
        controller.carregarDados(); // Supondo que este método exista para carregar os usuários na tabela
        view.setVisible(true);
    }

    /**
     * Navega para a tela de cadastro ou edição de um usuário.
     * @param janelaPai A janela anterior a ser escondida.
     * @param usuarioSelecionado O usuário a ser editado (null se for um novo cadastro).
     */
    public void toCadastroDeUsuario(JFrame janelaPai, Usuario usuarioSelecionado) {
        disposeJanelaPai(janelaPai);
        CadastroDeUsuario view = new CadastroDeUsuario(janelaPai, this, usuarioSelecionado);
        CadastroDeUsuarioController controller = new CadastroDeUsuarioController(view, this.usuarioService, this);
        view.setVisible(true);
    }
}