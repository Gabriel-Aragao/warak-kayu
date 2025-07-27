package br.edu.ifpb.biblioteca.warakkayu.shared.view;

import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesTelaPrincipalListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.*;

public class TelaPrincipal extends Janela {

    private JButton botaoUsuarios;
    private JButton botaoObras;
    private JButton botaoEmprestimos;
    private JButton botaoDevolucoes;
    private JButton botaoRelatorios;

    private AcoesTelaPrincipalListener listener; 

    public TelaPrincipal() {
        super();
        montarLayout();
    }

    private void montarLayout() {
        JLabel labelTitulo = new JLabel("Menu Principal");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        this.norte.add(labelTitulo);

        botaoUsuarios = criarBotaoMenu("Gerenciar Usuários");
        botaoObras = criarBotaoMenu("Gerenciar Obras");
        botaoEmprestimos = criarBotaoMenu("Realizar Empréstimo");
        botaoDevolucoes = criarBotaoMenu("Realizar Devolução");
        botaoRelatorios = criarBotaoMenu("Gerar Relatórios");

        botaoUsuarios.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarUsuarios();
            }
        });
        botaoObras.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarObras();
            }
        });
        botaoEmprestimos.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarEmprestimos();
            }
        });
        botaoDevolucoes.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarDevolucoes();
            }
        });
        botaoRelatorios.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarRelatorios();
            }
        });


        this.centro.add(botaoUsuarios, BorderLayout.CENTER);
        this.centro.add(botaoObras, BorderLayout.CENTER);
        this.centro.add(botaoEmprestimos, BorderLayout.CENTER);
        this.centro.add(botaoDevolucoes, BorderLayout.CENTER);
        this.centro.add(botaoRelatorios, BorderLayout.CENTER);
    }

    public void setListener(AcoesTelaPrincipalListener listener) {
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
    public JButton getBotaoDevolucoes() { 
        return botaoDevolucoes; 
    }
    public JButton getBotaoRelatorios() { 
        return botaoRelatorios; 
    }
}