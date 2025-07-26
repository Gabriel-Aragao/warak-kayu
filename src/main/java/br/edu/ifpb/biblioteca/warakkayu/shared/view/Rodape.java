package br.edu.ifpb.biblioteca.warakkayu.shared.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesDoRodapeListener;

public class Rodape extends JPanel {

    private JButton botaoCriar, botaoAtualizar, botaoRemover;
    
    private AcoesDoRodapeListener listener; 

    public Rodape() {
        super(new FlowLayout(FlowLayout.RIGHT)); 
        
        botaoCriar = new JButton("Criar");
        botaoAtualizar = new JButton("Atualizar");
        botaoRemover = new JButton("Remover");

        this.add(botaoCriar);
        this.add(botaoAtualizar);
        this.add(botaoRemover);
        
        botaoCriar.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarCriar();
            }
        });

        botaoAtualizar.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarAtualizar();
            }
        });
        
        botaoRemover.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarRemover();
            }
        });
        
    }

    public void setListener(AcoesDoRodapeListener listener) {
        this.listener = listener;
    }
    
    public JButton getBotaoCriar() {
        return botaoCriar;
    }

    public JButton getBotaoAtualizar() {
        return botaoAtualizar;
    }

    public JButton getBotaoRemover() {
        return botaoRemover;
    }

}