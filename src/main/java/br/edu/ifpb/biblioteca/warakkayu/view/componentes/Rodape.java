package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.ifpb.biblioteca.warakkayu.controller.AcoesDoPainelListener;
import br.edu.ifpb.biblioteca.warakkayu.model.TipoUsuario;


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

    public void aplicarPermissoes(TipoUsuario tipoUsuario) {
        switch (tipoUsuario) {
            case ADMIN:
                botaoCriar.setEnabled(true);
                botaoAtualizar.setEnabled(true);
                botaoRemover.setEnabled(true);
                botaoEmprestar.setEnabled(false);
                botaoDevolver.setEnabled(false);
                break;
            case BIBLIOTECARIO:
                botaoCriar.setEnabled(false);
                botaoAtualizar.setEnabled(false);
                botaoRemover.setEnabled(false);
                botaoEmprestar.setEnabled(true);
                botaoDevolver.setEnabled(true);
                break;
            case ESTAGIARIO:
            default:
                botaoCriar.setEnabled(false);
                botaoAtualizar.setEnabled(false);
                botaoRemover.setEnabled(false);
                botaoEmprestar.setEnabled(false); 
                botaoDevolver.setEnabled(true);
                break;
        }
    }
}