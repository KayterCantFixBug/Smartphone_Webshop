package DAO;

import model.Product;
import model.User;

public interface IBase<E> {
    void insert(E e);
    void update(E e);
    void delete(int id);
    E findById(int id);
}
