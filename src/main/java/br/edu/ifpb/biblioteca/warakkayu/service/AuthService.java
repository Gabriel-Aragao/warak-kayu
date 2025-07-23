package br.edu.ifpb.biblioteca.warakkayu.service;

import br.edu.ifpb.biblioteca.warakkayu.model.TipoUsuario;

public class AuthService {
    
    private TipoUsuario tipoUsuario;
    public AuthService() {
        this.tipoUsuario = TipoUsuario.ADMIN;
    }

    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }
}
