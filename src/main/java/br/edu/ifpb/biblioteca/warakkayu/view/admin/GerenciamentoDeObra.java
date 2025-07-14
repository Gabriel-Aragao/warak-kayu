package br.edu.ifpb.biblioteca.warakkayu.view.admin;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.edu.ifpb.biblioteca.warakkayu.view.componentes.BotaoVoltar;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Janela;

import java.awt.BorderLayout;


public class GerenciamentoDeObra extends Janela{

    private JTable tabela;
    private JScrollPane scroll;
    private BotaoVoltar voltar;

    public GerenciamentoDeObra() {
        super();

        String[] colunas = {"Código", "Título", "Autor", "Ano de Publicação", "Valor da Multa"};
        
        this.tabela = new JTable(new Object[0][0],colunas);
        this.tabela.setFillsViewportHeight(true);
        
        this.scroll = new JScrollPane(this.tabela);

        this.voltar = new BotaoVoltar(this);


        this.norte.add(this.voltar);
        this.centro.add(this.scroll);


    }
    
}
