package br.edu.ifpb.biblioteca.warakkayu.pagamento.service;

import br.edu.ifpb.biblioteca.warakkayu.pagamento.dao.PagamentoDao;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.Pagamento;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;

public class PagamentoService  {
    private PagamentoDao pagamentoDao;

    public PagamentoService(PagamentoDao pagamentoDao) {
        this.pagamentoDao = pagamentoDao;
    }

    public void add(Pagamento pagamento) throws PersistenciaException {
        this.pagamentoDao.add(pagamento);
    }
    
}
