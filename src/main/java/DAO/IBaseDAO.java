package DAO;

import java.util.List;

public interface IBaseDAO<E> {
    void insert(E e);
    void update(E e);
    <E> void delete(Class<E> entityClass, int id);
    <E> E findById(Class<E> entityClass, int id);
    <E> List<E> getAll(Class<E> entityClass);
}
