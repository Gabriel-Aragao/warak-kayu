package br.edu.ifpb.biblioteca.warakkayu.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.edu.ifpb.biblioteca.warakkayu.dao.EmprestimoDAO;
import br.edu.ifpb.biblioteca.warakkayu.dao.ObraDAO;
import br.edu.ifpb.biblioteca.warakkayu.dao.UsuarioDAO;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.EmprestimoNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoDisponivelException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.exceptions.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;

public class EmprestimoController {
    private UsuarioDAO usuarioDAO;
    private ObraDAO obraDAO;
    private EmprestimoDAO emprestimoDAO;
    private List<Usuario> usuarios;
    private List<Obra> obras;
    private List<Emprestimo> emprestimos;

    
    public EmprestimoController(UsuarioDAO usuarioDAO, ObraDAO obraDAO, EmprestimoDAO emprestimoDAO){
        this.emprestimoDAO = emprestimoDAO;
        this.obraDAO = obraDAO;
        this.usuarioDAO = usuarioDAO;
        this.usuarios = usuarioDAO.recuperar();
        this.obras  = obraDAO.recuperar();
        this.emprestimos = emprestimoDAO.recuperar();
    }

    public void realizarEmprestimo(String matriculaUsuario, long codigoObra) throws UsuarioNaoEncontradoException, ObraNaoEncontradaException, ObraNaoDisponivelException {
        Usuario usuario = this.buscarUsuario(matriculaUsuario);
        Obra obra = buscarObra(codigoObra);    
        if(!obra.isDisponivel()){
            throw new ObraNaoDisponivelException();
        }
        obra.emprestar();
        Emprestimo emprestimo = new Emprestimo(usuario, obra);
        emprestimos.add(emprestimo);
        this.emprestimoDAO.salvar(emprestimos);
        this.obraDAO.salvar(obras);
    }

    public double realizarDevolucao(long codigoObra) throws EmprestimoNaoEncontradoException, ObraNaoEncontradaException {
        Emprestimo emprestimo = this.buscarEmprestimo(codigoObra);
        Obra obra = this.buscarObra(codigoObra);
        Double multa = 0.0;
        obra.devolver();
        emprestimos.remove(emprestimo);
        if(LocalDate.now().isAfter(obra.calcularDataDevolucao())) {
            long diasDeDiferenca = ChronoUnit.DAYS.between(obra.calcularDataDevolucao(), LocalDate.now());
            multa = obra.calcularMulta((int) diasDeDiferenca);
        }
        obraDAO.salvar(obras);
        emprestimoDAO.salvar(emprestimos);
        return multa;
    }

    private Obra buscarObra(long codigoObra) throws ObraNaoEncontradaException{
        Obra obraEncotrada = null;
        for (Obra obra: this.obras){
            if (obra.getCodigo() == codigoObra) {
                obraEncotrada = obra;
            }
        }
        if(obraEncotrada == null) {
            throw new ObraNaoEncontradaException();
        }
        return obraEncotrada;
    }

    private Usuario buscarUsuario(String matriculaUsuario) throws UsuarioNaoEncontradoException {
        Usuario usuarioEncontrado = null;       
        for (Usuario usuario: this.usuarios){
            if (usuario.getMatricula().equals(matriculaUsuario)) {
                usuarioEncontrado = usuario;
            }
        }
        if(usuarioEncontrado == null) {
            throw new UsuarioNaoEncontradoException();
        }
        return usuarioEncontrado;
    }

    private Emprestimo buscarEmprestimo(long codigoObra) throws EmprestimoNaoEncontradoException{
        Emprestimo emprestimoEncontrado = null;
        for (Emprestimo emprestimo: this.emprestimos){
            if (emprestimo.getObra().getCodigo() == codigoObra) {
                emprestimoEncontrado = emprestimo;
            }
        }
        if(emprestimoEncontrado == null) {
            throw new EmprestimoNaoEncontradoException();
        }
        return emprestimoEncontrado;
    }
}
