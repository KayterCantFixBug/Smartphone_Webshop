package service;

import model.User;

import java.text.ParseException;
import java.util.List;

public interface IUserService {


    User login(String username, String password);

    User findByEmail(String email);

    boolean register(String name, String email, String password, String code);

    List<User> search(String keyword);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(String phone);
}
