package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BotaoVoltar extends JPanel {
    
    private JButton voltar;
    private JFrame janela;
    public BotaoVoltar(JFrame jFrame) {
        
        this.janela = jFrame;
        voltar = new JButton("< Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });

        this.add(voltar);
    }
}
