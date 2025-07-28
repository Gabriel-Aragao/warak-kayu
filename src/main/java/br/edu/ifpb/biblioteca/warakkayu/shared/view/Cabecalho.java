package br.edu.ifpb.biblioteca.warakkayu.shared.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Cabecalho extends JPanel {
    
    private JButton voltar;
    private JLabel tituloLabel;

    public Cabecalho(JFrame janelaPai, JFrame janelaAtual, String titulo) {
        super(new BorderLayout());

        this.tituloLabel = new JLabel(titulo);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 24);
        this.tituloLabel.setFont(fonteTitulo);
        this.tituloLabel.setHorizontalAlignment(SwingConstants.CENTER); 
        
        this.voltar = new JButton("< Voltar");
        this.voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(janelaPai != null) {
                    janelaPai.setVisible(true);
                }
                janelaAtual.setVisible(false);
            }
        });

        JPanel placeholderDireito = new JPanel();
        placeholderDireito.setOpaque(false);
        placeholderDireito.setPreferredSize(this.voltar.getPreferredSize());

        this.add(this.voltar, BorderLayout.WEST);
        this.add(this.tituloLabel, BorderLayout.CENTER);
        this.add(placeholderDireito, BorderLayout.EAST);
    }
}
