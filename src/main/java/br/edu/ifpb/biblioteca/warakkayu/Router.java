package br.edu.ifpb.biblioteca.warakkayu;

import javax.swing.JFrame;

import br.edu.ifpb.biblioteca.warakkayu.controller.CadastroDeObraController;
import br.edu.ifpb.biblioteca.warakkayu.controller.GerenciamentoDeObrasController;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.view.CadastroDeObra;
import br.edu.ifpb.biblioteca.warakkayu.view.GerenciamentoDeObras;
import br.edu.ifpb.biblioteca.warakkayu.view.RealizacaoEmprestimo;

public class Router {

    private ObraService obraService;
    private AuthService authService;
    private EmprestimoService emprestimoService;


    public Router(ObraService obraService, AuthService authService, EmprestimoService emprestimoService) {
        this.obraService = obraService;
        this.authService = authService;
        this.emprestimoService = emprestimoService;
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

    public void toEmprestimo(JFrame janelaPai, Obra obraSelecionada) {
        disposeJanelaPai(janelaPai);
        RealizacaoEmprestimo view = new RealizacaoEmprestimo(janelaPai, this, obraSelecionada);
        RealizacaoEmprestimoController controller = new RealizacaoEmprestimoController(view, emprestimoService, this);
        view.setVisible(true);
    }
}