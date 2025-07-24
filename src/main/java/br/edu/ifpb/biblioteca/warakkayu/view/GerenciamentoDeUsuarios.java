package br.edu.ifpb.biblioteca.warakkayu.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout; 

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario; 
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Janela;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.UsuariosTableModel;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Rodape;

public class GerenciamentoDeUsuarios extends Janela {
    
    private JTable tabela;
    private UsuariosTableModel usuariosTableModel;
    private JScrollPane scroll;
    private Cabecalho voltar;
    private Rodape painelAcoes;

    
    public GerenciamentoDeUsuarios(JFrame janelaPai, Router router) {
        super();
        
      
        this.voltar = new Cabecalho(this, null, "Usuários"); 
        
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
    
    // ✅ 4. MÉTODOS ESSENCIAIS ADICIONADOS
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