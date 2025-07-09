package br.edu.ifpb.biblioteca.warakkayu.view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifpb.biblioteca.warakkayu.controller.ObraController;
import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.StatusObra;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.CampoDeCadastro;

public class CadastroDeObra extends JFrame{
    private CampoDeCadastro codigo;
    private CampoDeCadastro titulo;
    private CampoDeCadastro autor;
    private CampoDeCadastro anoPublicacao;
    private CampoDeCadastro status;
    private CampoDeCadastro valorDaMulta;
    private JButton criarObra;

    public CadastroDeObra() {
        this.setTitle("Warak Kayu/cadastro/obra/new");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

        this.codigo = new CampoDeCadastro("Código: ", 20);
        this.titulo = new CampoDeCadastro("Título: ", 20);
        this.autor = new CampoDeCadastro("Autor: ", 20);
        this.anoPublicacao = new CampoDeCadastro("Ano da Publicação: ", 20);
        this.status = new CampoDeCadastro("Status: ", 20);
        this.valorDaMulta = new CampoDeCadastro("Valor da multa: ", 20);
        this.criarObra = new JButton("Criar");

        this.add(this.codigo);
        this.add(this.titulo);
        this.add(this.autor);
        this.add(this.anoPublicacao);
        this.add(this.status);
        this.add(this.valorDaMulta);

        this.add(this.criarObra);

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
