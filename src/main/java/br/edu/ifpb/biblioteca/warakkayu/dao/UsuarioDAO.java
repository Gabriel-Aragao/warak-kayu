package br.edu.ifpb.biblioteca.warakkayu.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import br.edu.ifpb.biblioteca.warakkayu.model.Usuario;

public class UsuarioDAO {
private Path path;

    public UsuarioDAO () {
        this.path = Paths.get("dados","usuarios.json");
    }

    public void salvar(List<Usuario> usuarios){
        String json = new Gson().toJson(usuarios);     
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> recuperar(){
        try {
            String jsonString = Files.readString(this.getPath());
            return Arrays.asList(new Gson().fromJson(jsonString, Usuario[].class));
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return new ArrayList<Usuario>();
    }

    private Path getPath(){
        return this.path;
    }
}
