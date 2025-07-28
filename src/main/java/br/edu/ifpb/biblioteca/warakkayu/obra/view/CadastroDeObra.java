package br.edu.ifpb.biblioteca.warakkayu.obra.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.TipoObra;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesDeCadastroListener;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.CampoDeCadastro;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TipoDeEntrada;

public class CadastroDeObra extends Janela{
    private JComboBox<TipoObra> tipo;
    private CampoDeCadastro codigo;
    
    private CampoDeCadastro titulo;
    private CampoDeCadastro autor;
    private CampoDeCadastro anoPublicacao;
    private CampoDeCadastro valorDaMulta;
    private JButton criarObra;
    private Cabecalho cabecalho;
    private AcoesDeCadastroListener listener; 
    private Obra obra;
    
    public CadastroDeObra(JFrame janelaPai, Router router, Obra obra) {
        super();
        this.obra = obra;
        this.iniciarTela(janelaPai);
        if(this.obra != null){
            this.preencherCampos();
        }
    }

    private void iniciarTela(JFrame janelaPai) {        
        this.cabecalho = new Cabecalho(janelaPai, this, "Obra");
        
        this.tipo = new JComboBox<TipoObra>(TipoObra.values());
        this.codigo = new CampoDeCadastro("Código: ", 20, TipoDeEntrada.INTEIRO);
        this.titulo = new CampoDeCadastro("Título: ", 20, TipoDeEntrada.TEXTO);
        this.autor = new CampoDeCadastro("Autor: ", 20, TipoDeEntrada.TEXTO);
        this.anoPublicacao = new CampoDeCadastro("Ano da Publicação: ", 20, TipoDeEntrada.INTEIRO);
        this.valorDaMulta = new CampoDeCadastro("Valor da multa: ", 20, TipoDeEntrada.DECIMAL);
        this.criarObra = new JButton("Salvar");
        
        this.norte.add(cabecalho);
        
        this.centro.add(this.tipo);
        this.centro.add(this.codigo);
        this.centro.add(this.titulo);
        this.centro.add(this.autor);
        this.centro.add(this.anoPublicacao);
        this.centro.add(this.valorDaMulta);
        
        this.sul.add(this.criarObra);
        
        this.criarObra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.aoClicarSalvar();
                }
            }
        });
    }
    
    public void preencherCampos() {
        this.tipo.setEnabled(false);
        this.codigo.setText(String.valueOf(this.obra.getCodigo()));
        this.titulo.setText(this.obra.getTitulo());
        this.autor.setText(this.obra.getAutor());
        this.anoPublicacao.setText(String.valueOf(this.obra.getAnoPublicacao()));
        this.valorDaMulta.setText(String.valueOf(this.obra.getValorDaMulta()));
    }

    public JComboBox<TipoObra> getTipo() {
        return this.tipo;
    }
    
    public CampoDeCadastro getCodigo() {
        return this.codigo;
    }
    
    public CampoDeCadastro getTitulo() {
        return this.titulo;
    }
    
    public CampoDeCadastro getAutor() {
        return this.autor;
    }
    
    public CampoDeCadastro getAnoPublicacao() {
        return this.anoPublicacao;
    }
        
    public CampoDeCadastro getValorDaMulta() {
        return this.valorDaMulta;
    }
    
    public JButton getCriarObra() {
        return this.criarObra;
    }
    
    public Cabecalho getCabecalho() {
        return this.cabecalho;
    }

    public Obra getObra() {
        return this.obra;
    }

    public void setListener(AcoesDeCadastroListener listener) {
        this.listener = listener;
    }
}
