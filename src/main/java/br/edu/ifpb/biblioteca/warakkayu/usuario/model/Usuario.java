package br.edu.ifpb.biblioteca.warakkayu.usuario.model;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private TipoUsuario tipoUsuario;
    
    public Usuario(String matricula, String nome, String email, String telefone, TipoUsuario tipoUsuario) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.matricula = matricula;
        this.tipoUsuario = tipoUsuario;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public TipoUsuario getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
