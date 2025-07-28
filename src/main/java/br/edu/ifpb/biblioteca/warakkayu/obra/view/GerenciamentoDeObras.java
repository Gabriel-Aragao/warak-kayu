package br.edu.ifpb.biblioteca.warakkayu.obra.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Rodape;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


public class GerenciamentoDeObras extends Janela {
    
    private Cabecalho cabecalho;
    private JTextField campoPesquisa;
    private JTable tabela;
    private ObrasTableModel obrasTableModel;
    private JScrollPane scroll;
    private JPanel painelPesquisa;
    private Rodape painelAcoes;

    
    public GerenciamentoDeObras(JFrame janelaPai, Router router) {
        super();
        
        this.cabecalho = new Cabecalho(janelaPai, this, "Obras");
        
        this.obrasTableModel = new ObrasTableModel();
        this.tabela = new JTable(this.obrasTableModel);
        this.tabela.setFillsViewportHeight(true);
        this.tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.scroll = new JScrollPane(this.tabela);
        JPanel painelConteudoCentral = new JPanel(new BorderLayout());
        this.painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.painelPesquisa.add(new JLabel("Pesquisar:"));
        this.campoPesquisa = new JTextField(30);
        this.painelPesquisa.add(this.campoPesquisa);

        painelConteudoCentral.add(painelPesquisa, BorderLayout.NORTH);
        painelConteudoCentral.add(this.scroll, BorderLayout.CENTER);
        
        this.painelAcoes = new Rodape();
        
        this.norte.add(this.cabecalho);
        this.centro.add(painelConteudoCentral);
        this.sul.add(this.painelAcoes, BorderLayout.CENTER);
        
    }

    public Obra getObraSelecionada() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = tabela.convertRowIndexToModel(selectedRow);
            return obrasTableModel.getObraAt(modelRow);
        }
        return null;
    }

    public JTable getTabela() {
        return this.tabela;
    }

    public JTextField getCampoPesquisa() { 
        return this.campoPesquisa; 
    }

    public ObrasTableModel getObrasTableModel() {
        return this.obrasTableModel;
    }

    public Cabecalho getCabecalho() {
        return this.cabecalho;
    }

    public Rodape getPainelAcoes() {
        return this.painelAcoes;
    }

}
