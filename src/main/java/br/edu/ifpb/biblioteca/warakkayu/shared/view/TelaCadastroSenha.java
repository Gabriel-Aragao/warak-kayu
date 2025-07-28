package br.edu.ifpb.biblioteca.warakkayu.shared.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesCadastroSenhaListener;

public class TelaCadastroSenha  extends Janela {


    private JPanel senhaPanel;
    private JLabel senhaLabel;
    private JPasswordField senhaPasswordField;
    private JPanel confirmarSenhaPanel;
    private JLabel confirmarSenhaLabel;
    private JPasswordField confirmarSenhaPasswordField;
    private JButton botaoCadastrar;
    private AcoesCadastroSenhaListener listener; 

    public TelaCadastroSenha(){
        super();
        JLabel labelTitulo = new JLabel("Cadastrar Senha");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        this.norte.add(labelTitulo);

        Font fonte = new Font("Arial", Font.BOLD, 16);
        
        this.senhaPanel = new JPanel(new BorderLayout(5, 5));
        
        this.senhaLabel = new JLabel("Senha:");
        this.senhaLabel.setPreferredSize(
            new Dimension(200, this.senhaLabel.getPreferredSize().height)
        );
        this.senhaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.senhaLabel.setFont(fonte);

        this.senhaPasswordField = new JPasswordField(20);
        this.senhaPasswordField.setFont(fonte);

        this.senhaPanel.add(senhaLabel, BorderLayout.WEST);
        this.senhaPanel.add(senhaPasswordField, BorderLayout.CENTER);

        this.confirmarSenhaPanel = new JPanel(new BorderLayout(5, 5));
        
        this.confirmarSenhaLabel = new JLabel("Confirmar Senha:");
        this.confirmarSenhaLabel.setPreferredSize(
            new Dimension(200, this.confirmarSenhaLabel.getPreferredSize().height)
        );
        this.confirmarSenhaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.confirmarSenhaLabel.setFont(fonte);

        this.confirmarSenhaPasswordField = new JPasswordField(20);
        this.confirmarSenhaPasswordField.setFont(fonte);

        this.confirmarSenhaPanel.add(confirmarSenhaLabel, BorderLayout.WEST);
        this.confirmarSenhaPanel.add(confirmarSenhaPasswordField, BorderLayout.CENTER);

        this.botaoCadastrar = new JButton("Cadastrar");
        this.botaoCadastrar.setFont(fonte);

        this.centro.add(this.senhaPanel);
        this.centro.add(this.confirmarSenhaPanel);
        this.centro.add(botaoCadastrar, BorderLayout.SOUTH);

        botaoCadastrar.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarCadastrar();
            }
        });
    }

    public void setListener(AcoesCadastroSenhaListener listener) {
        this.listener = listener;
    }


    public JPasswordField getSenha() {
        return this.senhaPasswordField;
    }

    public JPasswordField getConfirmarSenha() {
        return this.confirmarSenhaPasswordField;
    }

    public JButton getBotaoCadastrar() {
        return this.botaoCadastrar;
    }
}