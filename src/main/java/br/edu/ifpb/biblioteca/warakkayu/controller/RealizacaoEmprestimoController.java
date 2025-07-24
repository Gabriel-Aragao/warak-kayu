package br.edu.ifpb.biblioteca.warakkayu.controller;

import java.awt.Robot;

import javax.swing.JFrame;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.controller.listeners.EmprestimoListener;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.view.RealizacaoEmprestimo;

public class RealizacaoEmprestimoController implements EmprestimoListener{

    private RealizacaoEmprestimo view;
    private EmprestimoService emprestimoService;
    private Router router;

    public RealizacaoEmprestimoController(RealizacaoEmprestimo view, EmprestimoService emprestimoService, Router router){
        this.view = view;
        this.emprestimoService = emprestimoService;
        this.router = router;
    }

    @Override
    public void aoClicarEmprestar() {
        String matricula = this.view.getCampoMatriculaUsuario().getTextField().getText();
        Long codigo = Long.parseLong(this.view.getCampoCodigoObra().getTextField().getText());
        emprestimoService.realizarEmprestimo(matricula, codigo);
    }
    
}
