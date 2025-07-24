package br.edu.ifpb.biblioteca.warakkayu.dao;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import br.edu.ifpb.biblioteca.warakkayu.model.Devolucao;

public class DevolucaoDAO {
    private Path path;

    public DevolucaoDAO() {
        this.path = Paths.get("dados" , "devolucoes.json");
    }

    private void salvar(List<Devolucao> devolucoes) {
        String json = new Gson().toJson(devolucoes);
        try {
            Files.writeString(this.getPath(), json,  StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Devolucao> recuperar() {
        try {
        String jsonString = Files.readString(this.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Devolucao>(); 
    }
    public Path getPath() {
        return path;
    }
    
}
