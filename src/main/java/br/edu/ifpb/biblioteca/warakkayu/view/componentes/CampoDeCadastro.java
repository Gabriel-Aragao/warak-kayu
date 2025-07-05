package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CampoDeCadastro extends JPanel {

    private JLabel label;
    private JTextField textField;

    public CampoDeCadastro(String textoJLabel, int tamanhoTextField) {
        super();
        this.label = new JLabel(textoJLabel);
        this.textField = new JTextField(tamanhoTextField);
        this.textField.setMaximumSize(this.textField.getPreferredSize());


        this.add(this.label);
        this.add(this.textField);
    }

    public JTextField getTextField() {
        return this.textField;
    }

}