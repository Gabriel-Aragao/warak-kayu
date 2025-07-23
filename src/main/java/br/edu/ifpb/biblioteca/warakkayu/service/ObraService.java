package br.edu.ifpb.biblioteca.warakkayu.service;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.model.Artigo;
import br.edu.ifpb.biblioteca.warakkayu.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.Revista;
import br.edu.ifpb.biblioteca.warakkayu.model.TipoObra;

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

    public void save(Obra obra, long codigo, String titulo, String autor, 
            int anoPublicacao, double valorDaMulta, TipoObra tipoObra) 
            throws PersistenciaException, ObraNaoEncontradaException {

        if(obra == null) {
            switch(tipoObra) {
                case ARTIGO:
                    obra = new Artigo(codigo, titulo, autor, anoPublicacao, valorDaMulta);
                    break;
                case LIVRO:
                    obra = new Livro(codigo, titulo, autor, anoPublicacao, valorDaMulta);
                    break;
                case REVISTA:
                    obra = new Revista(codigo, titulo, autor, anoPublicacao, valorDaMulta);
                    break;
            }       
            this.add(obra);
        } else {
            obra.setCodigo(codigo);
            obra.setTitulo(titulo);
            obra.setAutor(autor);
            obra.setAnoPublicacao(anoPublicacao);
            obra.setValorDaMulta(valorDaMulta);
            this.update(obra.getId(), obra);
        }
    }
}
