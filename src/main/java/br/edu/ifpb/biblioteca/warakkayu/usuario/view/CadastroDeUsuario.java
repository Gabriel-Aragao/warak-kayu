package br.edu.ifpb.biblioteca.warakkayu.usuario.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.shared.controller.AcoesDeCadastroListener;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.CampoDeCadastro;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.Janela;
import br.edu.ifpb.biblioteca.warakkayu.shared.view.TipoDeEntrada;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class CadastroDeUsuario extends Janela {
    private JComboBox<TipoUsuario> tipo;
    private CampoDeCadastro matricula;
    private CampoDeCadastro nome;
    private CampoDeCadastro email;
    private CampoDeCadastro telefone;
    private JButton criarUsuario;
    private Cabecalho cabecalho;
    private AcoesDeCadastroListener listener;
    private Usuario usuario;

    public CadastroDeUsuario(JFrame janelaPai, Router router, Usuario usuario) {
        super();
        this.usuario = usuario;
        this.iniciarTela(janelaPai);
        if (this.usuario != null) {
            this.preencherCampos();
        }
    }

    private void iniciarTela(JFrame janelaPai) {
        this.cabecalho = new Cabecalho(janelaPai, this, "Usuário");
        this.tipo = new JComboBox<TipoUsuario>(TipoUsuario.values());
        this.matricula = new CampoDeCadastro("Matrícula: ", 20, TipoDeEntrada.TEXTO);
        this.nome = new CampoDeCadastro("Nome: ", 20, TipoDeEntrada.TEXTO);
        this.email = new CampoDeCadastro("Email: ", 20, TipoDeEntrada.TEXTO);
        this.telefone = new CampoDeCadastro("Telefone: ", 20, TipoDeEntrada.TEXTO);

        this.criarUsuario = new JButton("Salvar");
        this.criarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.aoClicarSalvar();
                }
            }
        });

        this.norte.add(cabecalho);
        
        this.centro.add(this.tipo);
        this.centro.add(this.matricula);
        this.centro.add(this.nome);
        this.centro.add(this.email);
        this.centro.add(this.telefone);

        this.sul.add(this.criarUsuario);

    }

    public void preencherCampos() {
        this.matricula.setText(this.usuario.getMatricula());
        this.nome.setText(this.usuario.getNome());
        this.email.setText(this.usuario.getEmail());
        this.telefone.setText(this.usuario.getTelefone());
    
    }

    public Cabecalho getCabecalho(){
        return this.cabecalho;
    }

    public JComboBox<TipoUsuario> getTipo() {
        return this.tipo;
    }

    public CampoDeCadastro getMatricula() {
        return this.matricula;
    }

    public CampoDeCadastro getNome() {
        return this.nome;
    }

    public CampoDeCadastro getEmail() {
        return this.email;
    }

    public CampoDeCadastro getTelefone() {
        return this.telefone;
    }

    public JButton getSalvarUsuario() {
        return this.criarUsuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setListener(AcoesDeCadastroListener listener) {
        this.listener = listener;
    }
}