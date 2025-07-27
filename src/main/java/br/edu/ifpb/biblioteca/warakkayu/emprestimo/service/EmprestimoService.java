package br.edu.ifpb.biblioteca.warakkayu.emprestimo.service;

import java.util.List;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.dao.EmprestimoDAO;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoDisponivelException;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.StatusObra;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoAptoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;

public class EmprestimoService{

    private EmprestimoDAO emprestimoDAO;
    private ObraService obraService;
    private UsuarioService usuarioService;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, ObraService obraService, UsuarioService usuarioService) {
        this.emprestimoDAO = emprestimoDAO;
        this.obraService = obraService;
        this.usuarioService = usuarioService;
    }

    public List<Obra> listObras(){
        return this.obraService.listObrasDisponiveis();
    }

    public List<Usuario> listLeitores(){
        return this.usuarioService.listLeitores();
    }

    public void realizarEmprestimo(Usuario usuario, Obra obra) 
            throws PersistenciaException, ObraNaoEncontradaException, 
            ObraNaoDisponivelException, UsuarioNaoAptoException
    {
        if(obra.getStatus() == StatusObra.EMPRESTADO) {
            throw new ObraNaoDisponivelException();
        }
        if(usuario.getTipoUsuario()!=TipoUsuario.LEITOR){
            throw new UsuarioNaoAptoException();
        }
        Emprestimo emprestimo = new Emprestimo(usuario, obra);
        this.emprestimoDAO.add(emprestimo);
        obra.emprestar();
        obraService.update(obra.getId(), obra);
    }
    
}
