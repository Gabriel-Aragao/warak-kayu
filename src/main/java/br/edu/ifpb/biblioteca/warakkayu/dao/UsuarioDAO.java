package br.edu.ifpb.biblioteca.warakkayu.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;

import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;

public class UsuarioDAO {
private Path path;

    public UsuarioDAO () {
        Path path = Paths.get("");
        path.resolve("../../../../../../../../../dados/obras.json");
        this.path = path;
    }

    public void salvar(Usuario usuario){
        List<Usuario> usuarios = List.of(recuperar());
        usuarios.add(usuario);
        String json = new Gson().toJson(usuarios);
        
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario[] recuperar(){
        try {
            String jsonString = Files.readString(this.getPath());
            return new Gson().fromJson(jsonString, Usuario[].class);
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return new Usuario[0];
    }

    private Path getPath(){
        return this.path;
    }
}
