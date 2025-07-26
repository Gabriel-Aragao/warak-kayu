package br.edu.ifpb.biblioteca.warakkayu.usuario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.StatusObra;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.service.CRUDService;
import br.edu.ifpb.biblioteca.warakkayu.usuario.dao.UsuarioDAO;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class UsuarioService implements CRUDService <Usuario>{
        private UsuarioDAO usuarioDao;

    public UsuarioService(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public List<Usuario> list() {
        return this.usuarioDao.list();
    }

    @Override
    public void add(Usuario usuario) throws PersistenciaException {
        this.usuarioDao.add(usuario);
    }

    @Override
    public void update(UUID id, Usuario usuario) throws PersistenciaException, UsuarioNaoEncontradoException {
        this.usuarioDao.update(id, usuario);
    }

    @Override
    public void delete(UUID id)  throws PersistenciaException, UsuarioNaoEncontradoException {
        this.usuarioDao.delete(id);
    }

    

    public List<Usuario> listLeitores(){
        List<Usuario> usuarios = this.usuarioDao.list();
        List<Usuario> leitores = new ArrayList<Usuario>();
        for (Usuario usuario : usuarios) {
            if (usuario.getTipoUsuario() == TipoUsuario.LEITOR) {
                leitores.add(usuario);
            }
        }
        return leitores;
    }

    public void save(Usuario usuario, String matricula, String nome, String email, String telefone, TipoUsuario tipoUsuario) 
            throws PersistenciaException, UsuarioNaoEncontradoException {

        if(usuario == null) {
            usuario = new Usuario(matricula, nome, email, telefone, tipoUsuario);   
            this.add(usuario);
        } else {
            usuario.setMatricula(matricula);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setTelefone(telefone);
            usuario.setTipoUsuario(tipoUsuario);
            this.update(usuario.getId(), usuario);
        }
    }

    @Override
    public Usuario findById(UUID id) throws NaoEncontradoException {
        return usuarioDao.findById(id);
    }    
}
