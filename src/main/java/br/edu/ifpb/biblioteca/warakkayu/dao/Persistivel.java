package br.edu.ifpb.biblioteca.warakkayu.dao;

import java.util.List;
import java.util.UUID;

import br.edu.ifpb.biblioteca.warakkayu.exceptions.PersistenciaException;

public interface Persistivel<T> {
    public void add(T t) throws PersistenciaException;
    public List<T> list();
    public void update(UUID id, T t) throws PersistenciaException, Exception;
    public void delete(UUID id) throws PersistenciaException, Exception;
}
