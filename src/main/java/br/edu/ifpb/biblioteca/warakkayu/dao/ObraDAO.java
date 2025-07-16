package br.edu.ifpb.biblioteca.warakkayu.dao;

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

import br.edu.ifpb.biblioteca.warakkayu.model.Livro;
import br.edu.ifpb.biblioteca.warakkayu.model.Obra;

public class ObraDAO implements Persistivel<Obra>{
    private Path path;
    private List<Obra> obras;

    public ObraDAO () {
        this.path = Paths.get("dados","obras.json");
        this.obras = this.recuperar();
    }

    private void salvar(List<Obra> obras) throws IOException{
        String json = new Gson().toJson(obras);
        Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
    }

    private List<Obra> recuperar(){
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

    private Obra getObraById(UUID id){
        for(Obra obra: this.obras){
            if(id.equals(obra.getId())){
                return obra;
            }
        }
        return null;
    }


    @Override
    public boolean add(Obra obra) {
        this.obras.add(obra);
        try {
            this.salvar(obras);
            return true;
        } catch (IOException e) {
            return false;
        }
    }



    @Override
    public List<Obra> list() {
        return new ArrayList<Obra>(this.obras);
    }



    @Override
    public boolean update(UUID id, Obra obra) {
        Obra obraEncontrada = getObraById(id);
        if(obraEncontrada != null){
            obraEncontrada.setCodigo(obra.getCodigo());
            obraEncontrada.setTitulo(obra.getTitulo());
            obraEncontrada.setAutor(obra.getAutor());
            obraEncontrada.setStatus(obra.getStatus());
            obraEncontrada.setTitulo(obra.getTitulo());
            obraEncontrada.setValorDaMulta(obra.getValorDaMulta());
            return true;
        }
        return false;
    }



    @Override
    public boolean delete(UUID id) {
        Obra obraEncontrada = getObraById(id);
        if(obraEncontrada!= null){
            obras.remove(obraEncontrada);
            return true;
        }
        return false;
    }
}
