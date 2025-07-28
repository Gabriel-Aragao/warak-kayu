package br.edu.ifpb.biblioteca.warakkayu.relatorio.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.edu.ifpb.biblioteca.warakkayu.relatorio.controller.AcoesRelatoriosListener;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;

public class TelaRelatorio extends Janela {

    private JButton botaoEmprestimos;
    private JButton botaoObras;
    private JButton botaoUsuarios;
    private Cabecalho voltar;

    private AcoesRelatoriosListener listener; 

    public TelaRelatorio(JFrame janelaPai) {
        super();
        montarLayout(janelaPai);
    }

    private void montarLayout(JFrame janelaPai) {
        this.voltar = new Cabecalho(janelaPai, this, "Relatorios");

        this.norte.add(this.voltar);

        botaoEmprestimos = criarBotaoMenu("Empréstimos do mês");
        botaoObras = criarBotaoMenu("Obras mais emprestadas");
        botaoUsuarios = criarBotaoMenu("Usuários com mais atrasos");

        botaoEmprestimos.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarEmprestimos();
            }
        });
        botaoObras.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarObras();
            }
        });
        botaoUsuarios.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarUsuarios();
            }
        });


        this.centro.add(botaoEmprestimos, BorderLayout.CENTER);
        this.centro.add(botaoObras, BorderLayout.CENTER);
        this.centro.add(botaoUsuarios, BorderLayout.CENTER);
    }

    public void setListener(AcoesRelatoriosListener listener) {
        this.listener = listener;
    }

    private JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setPreferredSize(new Dimension(200, 50));
        return botao;
    }

    public JButton getBotaoUsuarios() { 
        return botaoUsuarios; 
    }
    public JButton getBotaoObras() { 
        return botaoObras; 
    }
    public JButton getBotaoEmprestimos() { 
        return botaoEmprestimos; 
    }

}