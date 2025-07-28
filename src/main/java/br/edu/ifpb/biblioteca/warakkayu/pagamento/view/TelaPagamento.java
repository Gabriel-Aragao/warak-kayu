package br.edu.ifpb.biblioteca.warakkayu.pagamento.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.controller.AcoesDePagamentoListener;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.MetodoPagamento;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;

public class TelaPagamento extends Janela {
    private Cabecalho cabecalho;
    private JComboBox<MetodoPagamento> metodo;
    private JLabel valorLabel;
    private JLabel dataLabel;
    private JLabel usuarioLabel;
    private JLabel emprestimoLabel;
    private JButton confirmar;
    private AcoesDePagamentoListener listener; 

    public TelaPagamento(JFrame janelaPai, Router router, Emprestimo emprestimo) {
        this.cabecalho = new Cabecalho(janelaPai, this, "Registrar Pagamento");
        
        this.metodo = new JComboBox<MetodoPagamento>(MetodoPagamento.values());

        this.valorLabel = infoLabel("Valor da multa: R$"+String.valueOf(emprestimo.calcularMulta()));
        this.dataLabel = infoLabel(
            "Data Pagamento: "+LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.usuarioLabel = infoLabel("Usuário: "+emprestimo.getUsuario().getNome());
        this.emprestimoLabel = infoLabel("Empréstimo: "+emprestimo.getObra().getTitulo());

        this.confirmar = new JButton("Registrar Pagamento");

        this.norte.add(cabecalho);
        this.centro.add(metodo);
        this.centro.add(valorLabel);
        this.centro.add(dataLabel);
        this.centro.add(usuarioLabel);
        this.centro.add(emprestimoLabel);
        this.sul.add(confirmar);

        this.confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.aoClicarConfirmar();
                }
            }
        });
    }   

    private JLabel infoLabel(String texto) {
        Font fonte = new Font("Arial", Font.BOLD, 20);
        JLabel label = new JLabel(texto);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(fonte); 

        return label;
    }

    public void setListener(AcoesDePagamentoListener listener) {
        this.listener = listener;
    }

    public Cabecalho getCabecalho(){
        return this.cabecalho;
    }

    public JComboBox<MetodoPagamento> getMetodo() {
        return this.metodo;
    }
}
