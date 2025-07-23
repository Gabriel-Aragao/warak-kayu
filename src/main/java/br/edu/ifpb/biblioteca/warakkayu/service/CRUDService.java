package br.edu.ifpb.biblioteca.warakkayu.service;

import java.util.List;
import java.util.UUID;

public interface CRUDService<T> {
    public List<T> list();
    public void add(T t) throws Exception;
    public void update(UUID id, T t) throws Exception;
    public void delete(UUID id) throws Exception;
}
