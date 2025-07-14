package br.edu.ifpb.biblioteca.warakkayu.view.admin;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifpb.biblioteca.warakkayu.controller.ObraController;
import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.StatusObra;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.CampoDeCadastro;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Janela;

public class CadastroDeObra extends Janela{
    private CampoDeCadastro codigo;
    private CampoDeCadastro titulo;
    private CampoDeCadastro autor;
    private CampoDeCadastro anoPublicacao;
    private CampoDeCadastro status;
    private CampoDeCadastro valorDaMulta;
    private JButton criarObra;

    public CadastroDeObra() {
        super();
        
        this.codigo = new CampoDeCadastro("Código: ", 20);
        this.titulo = new CampoDeCadastro("Título: ", 20);
        this.autor = new CampoDeCadastro("Autor: ", 20);
        this.anoPublicacao = new CampoDeCadastro("Ano da Publicação: ", 20);
        this.status = new CampoDeCadastro("Status: ", 20);
        this.valorDaMulta = new CampoDeCadastro("Valor da multa: ", 20);
        this.criarObra = new JButton("Cadastrar");

        this.centro.add(this.codigo);
        this.centro.add(this.titulo);
        this.centro.add(this.autor);
        this.centro.add(this.anoPublicacao);
        this.centro.add(this.status);
        this.centro.add(this.valorDaMulta);

        this.sul.add(this.criarObra);

        this.criarObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarObra();
            }
        });

    }


    private void salvarObra(){

        long codigo = Long.parseLong(this.codigo.getTextField().getText().trim());
        String titulo = this.titulo.getTextField().getText().trim();
        String autor = this.autor.getTextField().getText().trim();
        int anoPublicacao = Integer.parseInt(this.anoPublicacao.getTextField().getText().trim());
        StatusObra status = StatusObra.DISPONIVEL;
        double valorDaMulta = Double.parseDouble(this.valorDaMulta.getTextField().getText().trim());

        Obra novaObra = new Livro(codigo, titulo, autor, anoPublicacao, status, valorDaMulta);

        ObraController obraController = new ObraController(new ObraDAO());
        obraController.cadastrarObra(novaObra);
    }
}
