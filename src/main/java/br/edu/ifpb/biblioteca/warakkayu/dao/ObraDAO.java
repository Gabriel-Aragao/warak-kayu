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

import br.edu.ifpb.biblioteca.warakkayu.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;

public class ObraDAO {
    private Path path;

    public ObraDAO () {
        this.path = Paths.get("dados","obras.json");
    }

    public void salvar(List<Obra> obras){
        String json = new Gson().toJson(obras);
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Obra> recuperar(){
        try {
            String jsonString = Files.readString(this.getPath());
            return new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonString, Livro[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return new ArrayList<Obra>();
    }

    private Path getPath(){
        return this.path;
    }
}
