package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.JFrame;

import br.edu.ifpb.biblioteca.warakkayu.obra.controller.CadastroDeObraController;
import br.edu.ifpb.biblioteca.warakkayu.obra.controller.GerenciamentoDeObrasController;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.obra.view.CadastroDeObra;
import br.edu.ifpb.biblioteca.warakkayu.obra.view.GerenciamentoDeObras;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.controller.TelaRelatorioController;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.service.RelatorioService;
import br.edu.ifpb.biblioteca.warakkayu.relatorio.view.TelaRelatorio;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.TelaCadastroSenhaController;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.TelaLoginController;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.TelaPrincipalController;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TelaCadastroSenha;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TelaLogin;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TelaPrincipal;
import br.edu.ifpb.biblioteca.warakkayu.usuario.controller.CadastroDeUsuarioController;
import br.edu.ifpb.biblioteca.warakkayu.usuario.controller.GerenciamentoDeUsuariosController;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.view.CadastroDeUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.view.GerenciamentoDeUsuarios;
import br.edu.ifpb.biblioteca.warakkayu.devolucao.controller.DevolucaoController;
import br.edu.ifpb.biblioteca.warakkayu.devolucao.view.TelaDevolucao;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.controller.RealizacaoEmprestimoController;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.view.RealizacaoEmprestimo;


public class Router {

    private ObraService obraService;
    private UsuarioService usuarioService; 
    private AuthService authService;
    private EmprestimoService emprestimoService;
    private RelatorioService relatorioService;


    public Router(
            ObraService obraService, AuthService authService, 
            EmprestimoService emprestimoService, UsuarioService usuarioService,
            RelatorioService relatorioService
        ) 
    {
        this.obraService = obraService;
        this.usuarioService = usuarioService; 
        this.authService = authService;
        this.emprestimoService = emprestimoService;
        this.relatorioService = relatorioService;
    }
    
    private void disposeJanelaPai(JFrame frame) {
        if(frame != null) {
            frame.setVisible(false);
        }
    }

    public void toGerenciamentoObras(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        GerenciamentoDeObras view = new GerenciamentoDeObras(janelaPai, this);
        GerenciamentoDeObrasController controller = new GerenciamentoDeObrasController(
            view, this.obraService, this);
        controller.carregarDados();
        view.setVisible(true);
    }

    public void toCadastroDeObra(JFrame janelaPai, Obra obraSelecionada) {
        disposeJanelaPai(janelaPai);
        CadastroDeObra view = new CadastroDeObra(janelaPai, this, obraSelecionada);
        new CadastroDeObraController(view, obraService, this);
        view.setVisible(true);
    }

    public void toEmprestimo(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        RealizacaoEmprestimo view = new RealizacaoEmprestimo(janelaPai, this);
        RealizacaoEmprestimoController controller = new RealizacaoEmprestimoController(
            view, authService, emprestimoService, this);
        controller.carregarDados();
        view.setVisible(true);
    }
   
    public void toGerenciamentoUsuarios(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        GerenciamentoDeUsuarios view = new GerenciamentoDeUsuarios(janelaPai, this);
        GerenciamentoDeUsuariosController controller = new GerenciamentoDeUsuariosController(
            view, this.usuarioService, this, this.authService);
        controller.carregarDados();
        view.setVisible(true);
    }

    public void toCadastroDeUsuario(JFrame janelaPai, Usuario usuarioSelecionado) {
        disposeJanelaPai(janelaPai);
        CadastroDeUsuario view = new CadastroDeUsuario(janelaPai, this, usuarioSelecionado);
        new CadastroDeUsuarioController(view, this.usuarioService, this);
        view.setVisible(true);
    }

    public void toTelaPrincipal(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        TelaPrincipal view = new TelaPrincipal();
        new TelaPrincipalController(view, this, this.authService);
        view.setVisible(true);
    }

    public void toTelaLogin() {
        TelaLogin view = new TelaLogin();
        new TelaLoginController(view, this, this.authService);
        view.setVisible(true);
    }

    public void toCadastroDeSenha(
            JFrame janelaPai, Router router, AuthService authService, String matricula
        )
    {   
        disposeJanelaPai(janelaPai);
        TelaCadastroSenha view = new TelaCadastroSenha();
        new TelaCadastroSenhaController(view, this, authService, matricula);

    }

    public void toDevolucao (JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        TelaDevolucao view = new TelaDevolucao(janelaPai, this);
        new DevolucaoController(view, emprestimoService, this);

    public void toRelatorios(JFrame janelaPai) {
        disposeJanelaPai(janelaPai);
        TelaRelatorio view = new TelaRelatorio(janelaPai);
        new TelaRelatorioController(view, this, this.emprestimoService, this.relatorioService);
        view.setVisible(true);
    }
}