package br.edu.ifpb.biblioteca.warakkayu.usuario.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout; 

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Rodape;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class GerenciamentoDeUsuarios extends Janela {
    
    private JTable tabela;
    private UsuariosTableModel usuariosTableModel;
    private JScrollPane scroll;
    private Cabecalho voltar;
    private Rodape painelAcoes;

    
    public GerenciamentoDeUsuarios(JFrame janelaPai, Router router) {
        super();
        
      
        this.voltar = new Cabecalho(janelaPai, this, "Usuários"); 
        
        this.usuariosTableModel = new UsuariosTableModel();
        this.tabela = new JTable(this.usuariosTableModel);
        this.tabela.setFillsViewportHeight(true);
        this.tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        this.scroll = new JScrollPane(this.tabela);
        
        this.painelAcoes = new Rodape();
        
        this.norte.add(this.voltar);
        this.centro.add(this.scroll);
        this.sul.add(this.painelAcoes, BorderLayout.CENTER);
        
    }
    
    public Usuario getUsuarioSelecionado() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            return usuariosTableModel.getUsuarioAt(selectedRow); 
        }
        return null;
    }

    public JTable getTabela() {
        return tabela;
    }

    public UsuariosTableModel getUsuariosTableModel() {
        return usuariosTableModel;
    }

    public JScrollPane getScroll() {
        return scroll;
    }

    public Cabecalho getVoltar() {
        return voltar;
    }

    public Rodape getPainelAcoes() {
        return painelAcoes;
    }
    
}