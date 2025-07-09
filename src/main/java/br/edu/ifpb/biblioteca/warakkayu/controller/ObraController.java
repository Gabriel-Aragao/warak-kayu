package br.edu.ifpb.biblioteca.warakkayu.controller;

import java.util.List;

import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;

public class ObraController {
    
    private ObraDAO obraDAO;
    private List<Obra> obras;

    public ObraController(ObraDAO obraDAO) {
        this.obraDAO = obraDAO;
    }

    public void cadastrarObra(Obra obra){
        this.obras = this.obraDAO.recuperar();
        this.obras.add(obra);
        this.obraDAO.salvar(obras);
    }
}
