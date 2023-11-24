package DAO;
import java.util.List;
import java.util.Optional;

import models.User;

public interface IUserDAO {
	List<User> findAll();
	User findOne(int id);
	User findOne(String email);
	void insert(User user);
	void update(User user);
	void delete(User user);
	boolean checkExistEmail(String email);
}
