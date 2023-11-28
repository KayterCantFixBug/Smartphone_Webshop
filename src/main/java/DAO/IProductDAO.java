package DAO;

import model.Product;
import model.User;

import java.util.List;

public interface IProductDAO {
    List<User> getAll();
    User findByEmail(String email);

}
