package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Janela extends JFrame{
    
    protected JPanel norte;
    protected JPanel sul;
    protected JPanel leste;
    protected JPanel oeste;
    protected JPanel centro;



    public Janela() {
        this.setTitle("Warak Kayu");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        this.norte = new JPanel();
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
    
}
