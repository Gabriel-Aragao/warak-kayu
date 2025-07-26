package br.edu.ifpb.biblioteca.warakkayu.obra.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.obra.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Artigo;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Revista;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.StatusObra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.TipoObra;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.CRUDService;

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

    @Override
    public Obra findById(UUID id) throws NaoEncontradoException {
        return obraDAO.findById(id);
    }   

    public List<Obra> listObrasDisponiveis(){
        List<Obra> obras = this.obraDAO.list();
        List<Obra> obrasDisponiveis = new ArrayList<Obra>();
        for (Obra obra : obras) {
            if (obra.getStatus() == StatusObra.DISPONIVEL) {
                obrasDisponiveis.add(obra);
            }
        }
        return obrasDisponiveis;
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
