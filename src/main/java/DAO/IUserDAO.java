package DAO;

import model.User;

import java.util.List;

public interface IUserDAO {
	List<User> getAll();
	User findById(int id);
	User findByEmail(String email);
	void insert(User user);
	void update(User user);
	void delete(User user);
	boolean checkExistEmail(String email);
	boolean checkExistPhone(String phoneNumber);
}
