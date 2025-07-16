package br.edu.ifpb.biblioteca.warakkayu.dao;

import java.util.List;
import java.util.UUID;

public interface Persistivel<T> {
    public boolean add(T t);
    public List<T> list();
    public boolean update(UUID id, T t);
    public boolean delete(UUID id);
}
