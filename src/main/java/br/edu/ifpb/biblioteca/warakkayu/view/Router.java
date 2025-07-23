package br.edu.ifpb.biblioteca.warakkayu.view;

import javax.swing.JFrame;

import br.edu.ifpb.biblioteca.warakkayu.controller.CadastroDeObraController;
import br.edu.ifpb.biblioteca.warakkayu.controller.GerenciamentoDeObrasController;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.service.AuthService;
import br.edu.ifpb.biblioteca.warakkayu.service.ObraService;

public class Router {

    private ObraService obraService;
    private AuthService authService;


    public Router(ObraService obraService, AuthService authService) {
        this.obraService = obraService;
        this.authService = authService;
    }
    
    private void setInvisible(JFrame frame) {
        if(frame != null) {
            frame.setVisible(false);
        }
    }

    public void toGerenciamentoObras(JFrame janelaPai) {
        setInvisible(janelaPai);
        GerenciamentoDeObras view = new GerenciamentoDeObras(janelaPai, this);
        GerenciamentoDeObrasController controller = new GerenciamentoDeObrasController(view, this.obraService, this, this.authService);
        controller.carregarDados();
        view.setVisible(true);
    }

    public void toCadastroDeObra(JFrame janelaPai) {
        setInvisible(janelaPai);
        CadastroDeObra view = new CadastroDeObra(janelaPai, this);
        CadastroDeObraController controller = new CadastroDeObraController(view, obraService, this);
        view.setVisible(true);
    }

    public void toEdicaoDeObra(JFrame janelaPai, Obra obraSelecionada) {
        setInvisible(janelaPai);
        CadastroDeObra view = new CadastroDeObra(janelaPai, this, obraSelecionada);
        CadastroDeObraController controller = new CadastroDeObraController(view, obraService, this);
        view.setVisible(true);
    }

}