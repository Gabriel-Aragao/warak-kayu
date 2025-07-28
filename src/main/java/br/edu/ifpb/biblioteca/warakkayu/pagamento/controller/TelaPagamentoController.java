package br.edu.ifpb.biblioteca.warakkayu.pagamento.controller;

import java.time.LocalDate;

import br.edu.ifpb.biblioteca.warakkayu.Router;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.obra.view.CadastroDeObra;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.MetodoPagamento;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.Pagamento;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.view.TelaPagamento;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;

public class TelaPagamentoController implements AcoesDePagamentoListener {

    private TelaPagamento view;
    private PagamentoService pagamentoService;
    private AuthService authService;
    private Router router;
    private Emprestimo emprestimo;

    public TelaPagamentoController(TelaPagamento view, PagamentoService pagamentoService, AuthService authService, Router router, Emprestimo emprestimo) {
        this.view = view;
        this.pagamentoService = pagamentoService;
        this.authService = authService;
        this.router = router;
        this.emprestimo = emprestimo;
        this.view.setListener(this);
    }

    @Override
    public void aoClicarConfirmar() {
        MetodoPagamento metodo = (MetodoPagamento) this.view.getMetodo().getSelectedItem();
        double multa = this.emprestimo.calcularMulta();
        Pagamento pagamento = new Pagamento(multa, LocalDate.now(), metodo, emprestimo.getUsuario(), authService.getUsuarioLogado(), emprestimo)
    }
    
}
