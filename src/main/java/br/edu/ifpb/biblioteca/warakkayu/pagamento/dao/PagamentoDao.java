package br.edu.ifpb.biblioteca.warakkayu.pagamento.dao;

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
import br.edu.ifpb.biblioteca.warakkayu.emprestimo.service.EmprestimoService;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.exception.PagamentoNaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.model.Pagamento;
import br.edu.ifpb.biblioteca.warakkayu.pagamento.util.PagamentoTypeAdapter;
import br.edu.ifpb.biblioteca.warakkayu.shared.dao.Persistivel;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;
import br.edu.ifpb.biblioteca.warakkayu.shared.util.LocalDateAdapter;
import br.edu.ifpb.biblioteca.warakkayu.usuario.service.UsuarioService;

public class PagamentoDao implements Persistivel<Pagamento> {
    private Path path;
    private List<Pagamento> pagamentos;
    private final Gson gson;

    public PagamentoDao(EmprestimoService emprestimoService, UsuarioService usuarioService) 
            throws PersistenciaException 
    {
        this.path = Paths.get("dados", "pagamentos.json");
        PagamentoTypeAdapter adapter = new PagamentoTypeAdapter(emprestimoService, usuarioService);
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Pagamento.class, adapter)
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
        this.pagamentos = this.recuperar();
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
        String json = this.gson.toJson(this.pagamentos);
        try {
            Files.writeString(this.getPath(), json, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenciaException("Não foi possível confirmar a operação.", e);
        }
    }

    private List<Pagamento> recuperar() throws PersistenciaException {
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
            return new ArrayList<>(Arrays.asList(this.gson.fromJson(jsonString, Pagamento[].class)));
        } catch (IOException e) {
            throw new PersistenciaException(
                    "Não foi possível recuperar os empréstimos salvos!", e
            );
        }
    }

    private Path getPath() {
        return this.path;
    }

    @Override
    public Pagamento findById(UUID id) throws PagamentoNaoEncontradoException{
        for (Pagamento pagamento : this.pagamentos) {
            if (id.equals(pagamento.getId())) {
                return pagamento;
            }
        }
        throw new PagamentoNaoEncontradoException();
    }

    @Override
    public void add(Pagamento pagamento) throws PersistenciaException {
        this.pagamentos.add(pagamento);
        this.salvar();
    }

    @Override
    public List<Pagamento> list() {
        return this.pagamentos;
    }

    @Override
    public void update(UUID id, Pagamento pagamento) throws PersistenciaException {
        throw new PersistenciaException(
            "Não é Possivel modificar um pagamento registrado"
        );
    }

    @Override
    public void delete(UUID id) throws PersistenciaException, EmprestimoNaoEncontradoException {
        throw new PersistenciaException(
            "Não é possível remover um pagamento registrado."
        );
    }
}