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

import br.edu.ifpb.biblioteca.warakkayu.model.Obra;

public class ObraDAO {
    private Path path;

    public ObraDAO () {
        this.path = Paths.get("dados","obras.json");
    }

    public void salvar(Obra obra){
        List<Obra> obras = new ArrayList<>(Arrays.asList(recuperar()));
        obras.add(obra);
        String json = new Gson().toJson(obras);
        
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Obra[] recuperar(){
        try {
            String jsonString = Files.readString(this.getPath());
            return new Gson().fromJson(jsonString, Obra[].class);
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return new Obra[0];
    }

    private Path getPath(){
        return this.path;
    }
}
