package br.edu.ifpb.biblioteca.warakkayu.service;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.dao.EmprestimoDAO;
import br.edu.ifpb.biblioteca.warakkayu.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;

public class EmprestimoService{

    private EmprestimoDAO emprestimoDAO;
    private ObraService obraService;
    private UsuarioService usuarioService;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, ObraService obraService, UsuarioService usuarioService) {
        this.emprestimoDAO = emprestimoDAO;
        this.obraService = obraService;
        this.usuarioService = usuarioService;
    }

    public void realizarEmprestimo(String matricula, Long codigo) {
        Usuario usuario = usuarioService.getUsuarioByMatricula(matricula);
        Obra obra = obraService.getObraByCodigo(codigo);

        Emprestimo emprestimo = new Emprestimo(usuario, obra);

        this.emprestimoDAO.add(emprestimo);
        obra.emprestar();
        obraService.update(obra.getId(), obra);
    }
    
}
