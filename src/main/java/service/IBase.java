package service;

import model.User;

import java.util.List;

public interface IBase <E>{
    List<E> getAll();
    void insert(E e);
    void update(E e);
    void delete(int id);
    E findById(int id);
}
