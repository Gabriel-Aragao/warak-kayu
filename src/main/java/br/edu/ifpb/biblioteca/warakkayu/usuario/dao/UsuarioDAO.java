package br.edu.ifpb.biblioteca.warakkayu.usuario.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.ifpb.biblioteca.warakkayu.shared.dao.Persistivel;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class UsuarioDAO implements Persistivel<Usuario> {
    private Path path;
    private List<Usuario> usuarios;
    private final Gson gson;

    public UsuarioDAO() throws PersistenciaException {
        this.path = Paths.get("dados", "usuarios.json");

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.usuarios = this.recuperar();
    }

    private void salvar() throws PersistenciaException {
        if (Files.notExists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new PersistenciaException(
                    "Não foi possível encontrar ou criar um diretorio de armazenamento.", e
                );
            }
        }
        String json = this.gson.toJson(this.usuarios);
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível confirmar a operação.", e);
        }
    }

    private List<Usuario> recuperar() throws PersistenciaException {
        if (Files.notExists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new PersistenciaException(
                    "Não foi possível encontrar ou criar um diretorio de armazenamento.", e
                );
            }
        }
        if (Files.notExists(this.getPath())) {
            return new ArrayList<>();
        }
        try {
            String jsonString = Files.readString(this.getPath());
            return new ArrayList<>(
                Arrays.asList(this.gson.fromJson(jsonString, Usuario[].class))
            );
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível recuperar as obras salvas!", e);
        }
    }

    private Path getPath() {
        return this.path;
    }

    @Override
    public void add(Usuario usuario) throws PersistenciaException {
        this.usuarios.add(usuario);
        this.salvar();
    }

    @Override

    public List<Usuario> list() {
        return this.usuarios;
    }

    @Override
    public void update(UUID id, Usuario usuario) throws PersistenciaException, UsuarioNaoEncontradoException {
        Usuario usuarioEncontrado = findById(id);
        if (usuarioEncontrado != null) {
            usuarioEncontrado.setNome(usuario.getNome());
            usuarioEncontrado.setEmail(usuario.getEmail());
            usuarioEncontrado.setTelefone(usuario.getTelefone());
            usuarioEncontrado.setTipoUsuario(usuario.getTipoUsuario());
            this.salvar();
        } else {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    public void delete(UUID id) throws PersistenciaException, UsuarioNaoEncontradoException {
        Usuario usuarioEncontrado = findById(id);
        if (usuarioEncontrado != null) {
            usuarios.remove(usuarioEncontrado);
            this.salvar();
        } else {
            throw new UsuarioNaoEncontradoException();
        }
    }

    @Override
    public Usuario findById(UUID id) throws UsuarioNaoEncontradoException {
       for (Usuario usuario : this.usuarios) {
            if (id.equals(usuario.getId())) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException();
    }

    public Usuario findByMatricula(String matricula) throws UsuarioNaoEncontradoException{
       for (Usuario usuario : this.usuarios) {
            if (matricula.equals(usuario.getMatricula())) {
                return usuario;
            }
        }
        throw new UsuarioNaoEncontradoException();
    }
    
    public void cadastrarSenha(String matricula, String senha) throws UsuarioNaoEncontradoException, PersistenciaException{
        Usuario usuario = this.findByMatricula(matricula);
        usuario.setSenha(senha);
        this.salvar();
    }
}