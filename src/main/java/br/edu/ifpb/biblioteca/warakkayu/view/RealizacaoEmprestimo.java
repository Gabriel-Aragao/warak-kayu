package br.edu.ifpb.biblioteca.warakkayu.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Cabecalho;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.CampoDeCadastro;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.Janela;
import br.edu.ifpb.biblioteca.warakkayu.view.componentes.TipoDeEntrada;

public class RealizacaoEmprestimo extends Janela {

    private Cabecalho cabecalho;
    private CampoDeCadastro campoMatriculaUsuario;
    private CampoDeCadastro campoCodigoObra;
    private JButton botaoEmprestar;

    public RealizacaoEmprestimo(JFrame janelaPai, Router navegador, Obra obraSelecionada) {
        super();

        this.cabecalho = new Cabecalho(janelaPai, this, "Realizar Empréstimo");
        this.campoMatriculaUsuario = new CampoDeCadastro("Matrícula do Usuário:", 20, TipoDeEntrada.INTEIRO);
        this.campoCodigoObra = new CampoDeCadastro("Código da Obra:", 20, TipoDeEntrada.INTEIRO);
        
        this.botaoEmprestar = new JButton("Emprestar");

        JPanel placeholderTop = new JPanel();
        placeholderTop.setOpaque(false);

        JPanel placeholderDown = new JPanel();
        placeholderDown.setOpaque(false);

        this.norte.add(cabecalho);
        this.centro.add(placeholderTop);
        this.centro.add(campoMatriculaUsuario);
        this.centro.add(campoCodigoObra);
        this.centro.add(placeholderDown);
        this.sul.add(botaoEmprestar);
    }

    public CampoDeCadastro getCampoMatriculaUsuario() {
        return campoMatriculaUsuario;
    }

    public CampoDeCadastro getCampoCodigoObra() {
        return campoCodigoObra;
    }

    public JButton getBotaoEmprestar() {
        return botaoEmprestar;
    }
}