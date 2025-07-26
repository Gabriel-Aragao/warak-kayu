package br.edu.ifpb.biblioteca.warakkayu.shared.service;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.NaoEncontradoException;
import br.edu.ifpb.biblioteca.warakkayu.shared.exceptions.PersistenciaException;

public interface CRUDService<T> {
    public List<T> list();
    public void add(T t) throws Exception;
    public void update(UUID id, T t) throws PersistenciaException, NaoEncontradoException;
    public void delete(UUID id) throws PersistenciaException, NaoEncontradoException;
    public T findById(UUID id) throws NaoEncontradoException;
}
