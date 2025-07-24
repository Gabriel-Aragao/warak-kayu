package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.ifpb.biblioteca.warakkayu.controller.AcoesDoPainelListener;

public class Rodape extends JPanel {

    private JButton botaoCriar, botaoAtualizar, botaoRemover, botaoEmprestar, botaoDevolver;
    
    private AcoesDoPainelListener listener; 

    public Rodape() {
        super(new FlowLayout(FlowLayout.RIGHT)); 
        
        botaoCriar = new JButton("Criar");
        botaoAtualizar = new JButton("Atualizar");
        botaoRemover = new JButton("Remover");
        botaoEmprestar = new JButton("Empréstimo");
        botaoDevolver = new JButton("Devolução");

        this.add(botaoCriar);
        this.add(botaoAtualizar);
        this.add(botaoRemover);
        this.add(botaoEmprestar);
        this.add(botaoDevolver);
        
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
        
        botaoEmprestar.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarEmprestar();
            }
        });
        
        botaoDevolver.addActionListener(e -> {
            if (listener != null) {
                listener.aoClicarDevolver();
            }
        });
    }

    public void setListener(AcoesDoPainelListener listener) {
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

    public JButton getBotaoEmprestar() {
        return botaoEmprestar;
    }

    public JButton getBotaoDevolver() {
        return botaoDevolver;
    }
}