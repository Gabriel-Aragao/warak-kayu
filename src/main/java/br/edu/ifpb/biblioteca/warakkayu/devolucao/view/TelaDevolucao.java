package br.edu.ifpb.biblioteca.warakkayu.devolucao.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.devolucao.controller.DevolucaoListener;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;

public class TelaDevolucao extends Janela {
    
    private Cabecalho cabecalho;
    private JTable tabelaEmprestimos;
    private DevolucaoTableModel devolucaoTableModel;
    private JScrollPane scrollEmprestimos;
    private JButton botaoDevolver;
    private DevolucaoListener listener;

    public TelaDevolucao(JFrame janelaPai, Router navegador) {
        super();
        
        this.cabecalho = new Cabecalho(janelaPai, this, "Realizar Devolução");
        
        this.devolucaoTableModel = new DevolucaoTableModel();
        this.tabelaEmprestimos = new JTable(this.devolucaoTableModel);
        this.tabelaEmprestimos.setFillsViewportHeight(true);
        this.tabelaEmprestimos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        
        this.scrollEmprestimos = new JScrollPane(this.tabelaEmprestimos);
        
        this.botaoDevolver = new JButton("Devolver Item Selecionado");
        this.botaoDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.aoClicarDevolver();;
                }
            }
        });
        
        this.norte.add(this.cabecalho);
        this.centro.add(this.scrollEmprestimos, BorderLayout.CENTER);
        this.sul.add(this.botaoDevolver);
    }

    public Emprestimo getDevolucaoSelecionada() {
        int selectedRow = tabelaEmprestimos.getSelectedRow();
        if (selectedRow != -1) {
            return devolucaoTableModel.getEmprestimoAt(selectedRow);
        }
        return null;
    }

    public DevolucaoTableModel getDevolucaoTableModel() {
        return devolucaoTableModel;
    }

    public void setListener(DevolucaoListener listener) {
        this.listener = listener;
    }
    
    public JButton getBotaoDevolver() {
        return botaoDevolver;
    }
}