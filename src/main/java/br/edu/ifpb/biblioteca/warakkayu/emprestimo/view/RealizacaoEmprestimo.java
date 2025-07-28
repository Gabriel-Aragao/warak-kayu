package br.edu.ifpb.biblioteca.warakkayu.emprestimo.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.controller.EmprestimoListener;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.view.ObrasTableModel;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.view.UsuariosTableModel;

public class RealizacaoEmprestimo extends Janela {

    private Cabecalho cabecalho;
    private JTable tabelaUsuarios;
    private JScrollPane scrollUsuarios;
    private UsuariosTableModel usuariosTableModel;  
    private JTable tabelaObras;
    private ObrasTableModel obrasTableModel;
    private JScrollPane scrollObras;
    private JButton botaoEmprestar;
    private EmprestimoListener listener;
    
    public RealizacaoEmprestimo(JFrame janelaPai, Router navegador) {
        super();
        
        this.cabecalho = new Cabecalho(janelaPai, this, "Realizar Empréstimo");
        
        this.usuariosTableModel = new UsuariosTableModel();
        this.tabelaUsuarios = new JTable(this.usuariosTableModel);
        this.tabelaUsuarios.setFillsViewportHeight(true);
        this.tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);   
        
        this.scrollUsuarios = new JScrollPane(this.tabelaUsuarios);
        
        this.obrasTableModel = new ObrasTableModel();
        this.tabelaObras = new JTable(this.obrasTableModel);
        this.tabelaObras.setFillsViewportHeight(true);
        this.tabelaObras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollObras = new JScrollPane(this.tabelaObras);
        
        this.botaoEmprestar = new JButton("Emprestar");
        this.botaoEmprestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.aoClicarEmprestar();
                }
            }
        });
        
        this.norte.add(this.cabecalho);
        this.centro.add(this.scrollUsuarios);
        this.centro.add(this.scrollObras);
        this.sul.add(this.botaoEmprestar);
    }

    public Usuario getUsuarioSelecionado() {
        int selectedRow = tabelaUsuarios.getSelectedRow();
        if (selectedRow != -1) {
            return usuariosTableModel.getUsuarioAt(selectedRow);
        }
        return null;
    }

    public Obra getObraSelecionada() {
        int selectedRow = tabelaObras.getSelectedRow();
        if (selectedRow != -1) {
            return obrasTableModel.getObraAt(selectedRow);
        }
        return null;
    }

    public JButton getBotaoEmprestar() {
        return botaoEmprestar;
    }
    public Cabecalho getCabecalho() {
        return cabecalho;
    }
    public JTable getTabelaUsuarios() {
        return tabelaUsuarios;
    }
    public UsuariosTableModel getUsuariosTableModel() {
        return usuariosTableModel;
    }
    public JTable getTabelaObras() {
        return tabelaObras;
    }
    public ObrasTableModel getObrasTableModel() {
        return obrasTableModel;
    }
    public void setListener(EmprestimoListener listener) {
        this.listener = listener;
    }
}