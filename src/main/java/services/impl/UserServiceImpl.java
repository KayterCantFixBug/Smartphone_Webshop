package services.impl;

import java.util.List;
import java.util.Optional;

import models.User;
import models.User.Role;
import models.User.Status;
import services.IUserService;
import DAO.IUserDAO;
import DAO.impl.UserDAOImpl;

public class UserServiceImpl implements IUserService {

	IUserDAO userDAO = new UserDAOImpl();

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

	@Override
	public User findOne(int id) {
		return userDAO.findOne(id);
	}

	@Override
	public User findOne(String email) {
		return userDAO.findOne(email);
	}

	@Override
	public void insert(User user) {
		userDAO.insert(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(User user) {
		userDAO.delete(user);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}

	@Override
	public boolean register(String name, String email, String password, String code) {
		if (userDAO.checkExistEmail(email))
			return false;
		userDAO.insert(new User(name, Role.USER, email, password, code, Status.INACTIVE));
		return true;
	}
}
