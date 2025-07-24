package br.edu.ifpb.biblioteca.warakkayu.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.controller.AcoesDeCadastroListener;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.CampoDeCadastro;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Janela;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.TipoDeEntrada;

public class CadastroDeUsuario extends Janela {
    private CampoDeCadastro matricula;
    private CampoDeCadastro nome;
    private CampoDeCadastro email;
    private CampoDeCadastro telefone;
    private CampoDeCadastro senha;
    private JButton criarUsuario;
    private Cabecalho botaoVoltar;
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

    /**
     * @param janelaPai
     */
    private void iniciarTela(JFrame janelaPai) {
        this.botaoVoltar = new Cabecalho(janelaPai, this, "Usuário");

        this.matricula = new CampoDeCadastro("Matrícula: ", 20, TipoDeEntrada.TEXTO);
        this.nome = new CampoDeCadastro("Nome: ", 20, TipoDeEntrada.TEXTO);
        this.email = new CampoDeCadastro("Email: ", 20, TipoDeEntrada.TEXTO);
        this.telefone = new CampoDeCadastro("Telefone: ", 20, TipoDeEntrada.TEXTO);
        this.senha = new CampoDeCadastro("Senha: ", 20, TipoDeEntrada.SENHA); 
        this.criarUsuario = new JButton("Salvar");

        this.norte.add(botaoVoltar);

        this.centro.add(this.matricula);
        this.centro.add(this.nome);
        this.centro.add(this.email);
        this.centro.add(this.telefone);
        this.centro.add(this.senha); 

        this.sul.add(this.criarUsuario);

        this.criarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener != null) {
                    listener.aoClicarSalvar();
                }
            }
        });
    }

    public void preencherCampos() {
        this.matricula.setText(this.usuario.getMatricula());
        this.nome.setText(this.usuario.getNome());
        this.email.setText(this.usuario.getEmail());
        this.telefone.setText(this.usuario.getTelefone());
    
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

    public CampoDeCadastro getSenha() {
        return this.senha;
    }

    public JButton getSalvarUsuario() {
        return this.criarUsuario;
    }

    public Cabecalho getBotaoVoltar() {
        return this.botaoVoltar;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setListener(AcoesDeCadastroListener listener) {
        this.listener = listener;
    }
}