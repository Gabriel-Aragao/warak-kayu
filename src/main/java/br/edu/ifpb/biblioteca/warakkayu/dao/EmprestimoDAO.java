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

import br.edu.ifpb.biblioteca.warakkayu.model.Emprestimo;

public class EmprestimoDAO {
    private Path path;

    public EmprestimoDAO () {
        this.path = Paths.get("dados","emprestimos.json");
    }

    public void salvar(List<Emprestimo> emprestimos){
        String json = new Gson().toJson(emprestimos);
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> recuperar(){
        try {
            String jsonString = Files.readString(this.getPath());
            return new ArrayList<>(Arrays.asList(new Gson().fromJson(jsonString, Emprestimo[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return new ArrayList<Emprestimo>();
    }

    private Path getPath(){
        return this.path;
    }
}
