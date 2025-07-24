package br.edu.ifpb.biblioteca.warakkayu.view.componentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

public class CampoDeCadastro extends JPanel {

    private JLabel label;
    private JFormattedTextField formattedTextField;

    public CampoDeCadastro(String textoJLabel, int tamanhoTextField, TipoDeEntrada tipo) {
        super(new BorderLayout(5, 5));

        Font fonte = new Font("Arial", Font.BOLD, 16);

        this.label = new JLabel(textoJLabel);
        this.label.setPreferredSize(new Dimension(200, this.label.getPreferredSize().height));
        this.label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.label.setFont(fonte);

        if (tipo == TipoDeEntrada.DECIMAL) {
            NumberFormat formatoDecimal = NumberFormat.getNumberInstance(new Locale("en", "US"));
            formatoDecimal.setMinimumFractionDigits(2);
            formatoDecimal.setMaximumFractionDigits(2);

            NumberFormatter formatadorDecimal = new NumberFormatter(formatoDecimal);
            formatadorDecimal.setValueClass(Double.class);
            formatadorDecimal.setAllowsInvalid(false);
            formatadorDecimal.setMinimum(0.0);

            this.formattedTextField = new JFormattedTextField(formatadorDecimal);
            this.formattedTextField.setValue(0.0); 
        } else if (tipo == TipoDeEntrada.INTEIRO) {
            NumberFormat formatoInteiro = NumberFormat.getIntegerInstance();
            formatoInteiro.setGroupingUsed(false); 

            NumberFormatter formatadorNumerico = new NumberFormatter(formatoInteiro);
            formatadorNumerico.setValueClass(Long.class);
            formatadorNumerico.setAllowsInvalid(false);
            formatadorNumerico.setMinimum(0L);

            this.formattedTextField = new JFormattedTextField(formatadorNumerico);
            this.formattedTextField.setValue(0L);
         
        } else{
            this.formattedTextField = new JFormattedTextField();
        }
        this.formattedTextField.setFont(fonte);
        this.add(this.label, BorderLayout.WEST);
        this.add(this.formattedTextField, BorderLayout.CENTER);
    }

    public JFormattedTextField getTextField() {
        return this.formattedTextField;
    }

    public void setText(String text) {
        this.formattedTextField.setText(text);
    }

}