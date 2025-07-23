package br.edu.ifpb.biblioteca.warakkayu.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Janela;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.ObrasTableModel;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Rodape;

import java.awt.BorderLayout;


public class GerenciamentoDeObras extends Janela {

    private JTable tabela;
    private ObrasTableModel obrasTableModel;
    private JScrollPane scroll;
    private Cabecalho voltar;
    private Rodape painelAcoes;

    
    public GerenciamentoDeObras(JFrame janelaPai, Router router) {
        super();
        
        this.voltar = new Cabecalho(this, null, "Obras");
        
        this.obrasTableModel = new ObrasTableModel();
        this.tabela = new JTable(this.obrasTableModel);
        this.tabela.setFillsViewportHeight(true);
        this.tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.scroll = new JScrollPane(this.tabela);
        
        this.painelAcoes = new Rodape();
        
        this.norte.add(this.voltar);
        this.centro.add(this.scroll);
        this.sul.add(this.painelAcoes, BorderLayout.CENTER);
        
    }

    public Obra getObraSelecionada() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            return obrasTableModel.getObraAt(selectedRow);
        }
        return null;
    }

    public JTable getTabela() {
        return this.tabela;
    }

    public ObrasTableModel getObrasTableModel() {
        return this.obrasTableModel;
    }

    public JScrollPane getScroll() {
        return this.scroll;
    }

    public Cabecalho getVoltar() {
        return this.voltar;
    }

    public Rodape getPainelAcoes() {
        return this.painelAcoes;
    }

}
