package br.edu.ifpb.biblioteca.warakkayu.pagamento.controller;

import java.time.LocalDate;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.MetodoPagamento;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.Pagamento;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.service.PagamentoService;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.view.TelaPagamento;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.AuthService;

public class TelaPagamentoController implements AcoesDePagamentoListener {

    private TelaPagamento view;
    private PagamentoService pagamentoService;
    private AuthService authService;
    private Emprestimo emprestimo;

    public TelaPagamentoController(TelaPagamento view, PagamentoService pagamentoService, AuthService authService, Emprestimo emprestimo) {
        this.view = view;
        this.pagamentoService = pagamentoService;
        this.authService = authService;
        this.emprestimo = emprestimo;
        this.view.getCabecalho().getVoltar().setEnabled(false);
        this.view.setListener(this);
    }

    @Override
    public void aoClicarConfirmar() {
        MetodoPagamento metodo = (MetodoPagamento) this.view.getMetodo().getSelectedItem();
        double multa = this.emprestimo.calcularMulta();
        Pagamento pagamento = new Pagamento(multa, LocalDate.now(), metodo, emprestimo.getUsuario(), authService.getUsuarioLogado(), emprestimo);
        try {
            pagamentoService.add(pagamento);
            this.view.exibirMensagem("Pagamento registrado com sucesso.");
            this.view.getCabecalho().getVoltar().setEnabled(true);
            this.view.getCabecalho().getVoltar().doClick();
        } catch (PersistenciaException e) {
            this.view.exibirErro("Erro ao registrar o pagamento.\nTente Novamente!");
        }
    }
    
}
