package br.edu.ifpb.biblioteca.warakkayu.shared.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesLoginListener;


public class TelaLogin extends Janela {

    private CampoDeCadastro matricula;
    private JPanel senhaPanel;
    private JLabel senhaLabel;
    private JPasswordField senhaPasswordField;
    private JButton botaoEntrar;
    private AcoesLoginListener listener; 

    public TelaLogin(){
        super();
        JLabel labelTitulo = new JLabel("Login");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        this.norte.add(labelTitulo);

        this.matricula = new CampoDeCadastro("Matrícula:", 20, TipoDeEntrada.TEXTO);
        
        Font fonte = new Font("Arial", Font.BOLD, 16);
        
        this.senhaPanel = new JPanel(new BorderLayout(5, 5));
        
        this.senhaLabel = new JLabel("Senha:");
        this.senhaLabel.setPreferredSize(new Dimension(200, this.senhaLabel.getPreferredSize().height));
        this.senhaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        this.senhaLabel.setFont(fonte);

        this.senhaPasswordField = new JPasswordField(20);
        this.senhaPasswordField.setFont(fonte);

        this.senhaPanel.add(senhaLabel, BorderLayout.WEST);
        this.senhaPanel.add(senhaPasswordField, BorderLayout.CENTER);

        this.botaoEntrar = new JButton("Entrar");
        this.botaoEntrar.setFont(fonte);

        this.centro.add(this.matricula);
        this.centro.add(this.senhaPanel);
        this.centro.add(botaoEntrar, BorderLayout.SOUTH);

        botaoEntrar.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarLogin();
            }
        });
    }

    public void setListener(AcoesLoginListener listener) {
        this.listener = listener;
    }

    public CampoDeCadastro getMatricula() {
        return this.matricula;
    }

    public JPasswordField getSenha() {
        return this.senhaPasswordField;
    }

    public JButton getBotaoLogin() {
        return this.botaoEntrar;
    }
    
}
