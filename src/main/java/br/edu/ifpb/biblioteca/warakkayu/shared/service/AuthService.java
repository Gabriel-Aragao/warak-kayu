package br.edu.ifpb.biblioteca.warakkayu.shared.service;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.AuthenticacaoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.ModificacaoDeSenhaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.SenhaNaoCadastradaException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;

public class AuthService {
    
    private Usuario usuarioLogado;
    private UsuarioService usuarioService;
    
    public AuthService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public TipoUsuario getTipoUsuario() {
        return this.usuarioLogado.getTipoUsuario();
    }


    public void cadastrarSenha(String matricula, String senhaEmTexto) 
            throws NaoEncontradoException, ModificacaoDeSenhaException, PersistenciaException 
    {        
        String hashDaSenha = BCrypt.hashpw(senhaEmTexto, BCrypt.gensalt());
        Usuario usuario = usuarioService.findByMatricula(matricula);
        String senhaAtual = usuario.getSenha();
        if(senhaAtual!=null){
            throw new ModificacaoDeSenhaException();
        }
        usuarioService.cadastrarSenha(matricula, hashDaSenha);
    }

    public void autenticar(String matricula, String senhaEmTexto) 
            throws AuthenticacaoException, SenhaNaoCadastradaException 
    {
        try {
            Usuario usuario = usuarioService.findByMatricula(matricula);
             String hashArmazenado = usuario.getSenha();
             if(usuario.getTipoUsuario() == TipoUsuario.REMOVIDO){
                throw new AuthenticacaoException();
             }
             if(hashArmazenado == null) {
                throw new SenhaNaoCadastradaException();
             }
            if (BCrypt.checkpw(senhaEmTexto, hashArmazenado)) {
                this.usuarioLogado = usuario;
            } else {
                throw new AuthenticacaoException();
            }
        } catch (NaoEncontradoException e) {
            throw new AuthenticacaoException();
        }
    }

    public Usuario getUsuarioLogado() {
        return this.usuarioLogado;
    }
}
