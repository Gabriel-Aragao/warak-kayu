package br.edu.ifpb.biblioteca.warakkayu.shared.service;

import br.edu.ifpb.biblioteca.warakkayu.usuario.model.TipoUsuario;

public class AuthService {
    
    private TipoUsuario tipoUsuario;
    public AuthService() {
        this.tipoUsuario = TipoUsuario.LEITOR;
    }

    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }
}
