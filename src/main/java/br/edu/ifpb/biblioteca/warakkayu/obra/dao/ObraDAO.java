package br.edu.ifpb.biblioteca.warakkayu.obra.dao;

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

import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.Obra;
import br.edu.ifpb.biblioteca.warakkayu.obra.model.StatusObra;
import br.edu.ifpb.biblioteca.warakkayu.obra.util.ObraDeserializer;
import br.edu.ifpb.biblioteca.warakkayu.shared.dao.Persistivel;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.exception.UsuarioNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.usuario.model.Usuario;

public class ObraDAO implements Persistivel<Obra> {
    private Path path;
    private List<Obra> obras;
    private final Gson gson;

    public ObraDAO() throws PersistenciaException {
        this.path = Paths.get("dados", "obras.json");

        this.gson = new GsonBuilder()
                .registerTypeAdapter(Obra.class, new ObraDeserializer())
                .setPrettyPrinting()
                .create();

        this.obras = this.recuperar();
    }

    private void salvar() throws PersistenciaException {
        if (Files.notExists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new PersistenciaException("Não foi possível encontrar ou criar um diretorio de armazenamento.",
                        e);
            }
        }
        String json = this.gson.toJson(this.obras);
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível confirmar a operação.", e);
        }
    }

    private List<Obra> recuperar() throws PersistenciaException {
        if (Files.notExists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new PersistenciaException("Não foi possível encontrar ou criar um diretorio de armazenamento.",
                        e);
            }
        }
        if (Files.notExists(this.getPath())) {
            return new ArrayList<>();
        }
        try {
            String jsonString = Files.readString(this.getPath());
            return new ArrayList<>(Arrays.asList(this.gson.fromJson(jsonString, Obra[].class)));
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível recuperar as obras salvas!", e);
        }
    }

    private Path getPath() {
        return this.path;
    }

    @Override
    public void add(Obra obra) throws PersistenciaException {
        this.obras.add(obra);
        this.salvar();
    }

    @Override

    public List<Obra> list() {
        return this.obras;
    }

    @Override
    public void update(UUID id, Obra obra) throws PersistenciaException, ObraNaoEncontradaException {
        Obra obraEncontrada = this.findById(id);
        if (obraEncontrada != null) {
            obraEncontrada.setCodigo(obra.getCodigo());
            obraEncontrada.setTitulo(obra.getTitulo());
            obraEncontrada.setAutor(obra.getAutor());
            obraEncontrada.setTitulo(obra.getTitulo());
            obraEncontrada.setValorDaMulta(obra.getValorDaMulta());
            this.salvar();
        } else {
            throw new ObraNaoEncontradaException();
        }
    }

    @Override
    public void delete(UUID id) throws PersistenciaException, ObraNaoEncontradaException {
        Obra obraEncontrada = this.findById(id);
        if (obraEncontrada != null) {
            obras.remove(obraEncontrada);
            this.salvar();
        } else {
            throw new ObraNaoEncontradaException();
        }
    }

    @Override
    public Obra findById(UUID id) throws ObraNaoEncontradaException {
                for (Obra obra : this.obras) {
            if (id.equals(obra.getId())) {
                return obra;
            }
        }
        throw new ObraNaoEncontradaException();
    }

    public Obra findByCodigo(Long codigo) throws ObraNaoEncontradaException{
       for (Obra obra : this.obras) {
            if (codigo == obra.getCodigo()) {
                return obra;
            }
        }
        throw new ObraNaoEncontradaException();
    }
    
}