package service;

import model.User;

import java.util.List;

public interface IUserService {
    void insert(User user);
    void update(User user);
    void delete(User user);

    User login(String email, String password);

    User findById(int id);
    User findByEmail(String email);

    boolean register(String name, String email, String password, String code);

    List<User> getAll();
    List<User> search(String keyword);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
}
