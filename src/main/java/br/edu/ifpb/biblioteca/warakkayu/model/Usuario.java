package br.edu.ifpb.biblioteca.warakkayu.model;

public class Usuario {
    private String nome;
    private String matricula;
    private TipoUsuario tipoUsuario;
    private String telefone;
    private String email;
    
    public Usuario(String nome, String matricula, TipoUsuario tipoUsuario, String telefone, String email) {
        this.nome = nome;
        this.matricula = matricula;
        this.tipoUsuario = tipoUsuario;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
