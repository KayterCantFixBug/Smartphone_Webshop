package service;

import java.util.List;

public interface IBaseService<E>{
    <E> List<E> getAll(Class<E> entityClass);
    void insert(E e);
    void update(E e);
    <E> void delete(Class<E> entityClass, int id);
    <E> E findById(Class<E> entityClass, int id);
}
