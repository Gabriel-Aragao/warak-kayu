package br.edu.ifpb.biblioteca.warakkayu.emprestimo.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.ifpb.biblioteca.warakkayu.emprestimo.exception.EmprestimoNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.Emprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.model.StatusEmprestimo;
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.util.EmprestimoTypeAdapter;
import br.edu.ifpb.biblioteca.warakkayu.obra.exception.ObraNaoEncontradaException;
import br.edu.ifpb.biblioteca.warakkayu.obra.service.ObraService;
import br.edu.ifpb.biblioteca.warakkayu.shared.dao.Persistivel;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.util.LocalDateAdapter;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;

public class EmprestimoDAO implements Persistivel<Emprestimo> {
    private Path path;
    private List<Emprestimo> emprestimos;
    private final Gson gson;

    public EmprestimoDAO(UsuarioService usuarioService, ObraService obraService) 
            throws PersistenciaException 
    {
        this.path = Paths.get("dados", "emprestimos.json");
        EmprestimoTypeAdapter adapter = new EmprestimoTypeAdapter(usuarioService, obraService);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Emprestimo.class, adapter)
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        this.emprestimos = this.recuperar();
    }

    private void salvar() throws PersistenciaException {
        if (Files.notExists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new PersistenciaException(
                    "Não foi possível encontrar ou criar um diretorio de armazenamento.",
                     e
                );
            }
        }
        String json = this.gson.toJson(this.emprestimos);
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível confirmar a operação.", e);
        }
    }

    private List<Emprestimo> recuperar() throws PersistenciaException {
        if (Files.notExists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new PersistenciaException(
                    "Não foi possível encontrar ou criar um diretorio de armazenamento.", 
                    e
                );
            }
        }
        if (Files.notExists(this.getPath())) {
            return new ArrayList<>();
        }
        try {
            String jsonString = Files.readString(this.getPath());
            return new ArrayList<>(Arrays.asList(this.gson.fromJson(jsonString, Emprestimo[].class)));
        } catch (IOException e) {
            throw new PersistenciaException(
                    "Não foi possível recuperar os empréstimos salvos!", e
            );
        }
    }

    private Path getPath() {
        return this.path;
    }

    public Emprestimo findById(UUID id) {
        for (Emprestimo emprestimo : this.emprestimos) {
            if (id.equals(emprestimo.getId())) {
                return emprestimo;
            }
        }
        return null;
    }

    @Override
    public void add(Emprestimo emprestimo) throws PersistenciaException {
        this.emprestimos.add(emprestimo);
        this.salvar();
    }

    @Override
    public List<Emprestimo> list() {
        return this.emprestimos;
    }

    public List<Emprestimo> listEmprestimosEmCurso() {
        List<Emprestimo> emprestimosEmCurso = new ArrayList<Emprestimo>();
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getStatusEmprestimo() == StatusEmprestimo.EM_CURSO) {
                emprestimosEmCurso.add(emprestimo);
            }
        }
        return emprestimosEmCurso;
    }

    @Override
    public void update(UUID id, Emprestimo emprestimo) throws PersistenciaException, ObraNaoEncontradaException {
        throw new PersistenciaException(
            "A única alteração possível deve ser realizada através da devolução"
        );
    }

    @Override
    public void delete(UUID id) throws PersistenciaException, EmprestimoNaoEncontradoException {
        throw new PersistenciaException(
            "Não é possível remover um emprestimo registrado."
        );
    }


}