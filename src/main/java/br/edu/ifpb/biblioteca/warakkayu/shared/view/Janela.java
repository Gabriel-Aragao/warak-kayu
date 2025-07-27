package br.edu.ifpb.biblioteca.warakkayu.shared.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Janela extends JFrame{
    
    protected JPanel norte;
    protected JPanel sul;
    protected JPanel leste;
    protected JPanel oeste;
    protected JPanel centro;



    public Janela() {
        this.setTitle("Warak Kayu");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.norte = new JPanel(new BorderLayout());
        this.sul = new JPanel();
        this.leste = new JPanel();
        this.oeste = new JPanel();
        this.centro = new JPanel();

        this.centro.setLayout(new GridLayout(0, 1, 0, 5)); 

        this.add(norte, BorderLayout.NORTH);
        this.add(sul, BorderLayout.SOUTH);
        this.add(leste, BorderLayout.EAST);
        this.add(oeste, BorderLayout.WEST);
        this.add(centro, BorderLayout.CENTER);
    }
    
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(
            null, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(
            null, mensagem, "Aviso!", JOptionPane.WARNING_MESSAGE
        );
    }

    public void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(
            null, mensagem, "Erro!!", JOptionPane.ERROR_MESSAGE
        );
    }

    public boolean exibirConfirmacao(String mensagem) {
        int resposta = JOptionPane.showConfirmDialog(
            null, 
            mensagem, 
            "Confirmar?", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        return resposta == JOptionPane.YES_OPTION;
    }
}
