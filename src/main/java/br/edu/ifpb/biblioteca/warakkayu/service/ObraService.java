package br.edu.ifpb.biblioteca.warakkayu.service;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;

public class ObraService implements CRUDService<Obra>{
        private ObraDAO obraDAO;

    public ObraService(ObraDAO obraDAO) {
        this.obraDAO = obraDAO;
    }

    @Override
    public List<Obra> list() {
        return this.obraDAO.list();
    }

    @Override
    public void add(Obra obra) throws PersistenciaException {
        this.obraDAO.add(obra);
    }

    @Override
    public void update(UUID id, Obra obra) throws PersistenciaException, ObraNaoEncontradaException {
        this.obraDAO.update(id, obra);
    }

    @Override
    public void delete(UUID id)  throws PersistenciaException, ObraNaoEncontradaException {
        this.obraDAO.delete(id);
    }

}
