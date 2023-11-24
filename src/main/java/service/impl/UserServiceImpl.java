package service.impl;

import DAO.IUserDAO;
import DAO.impl.UserDAOImpl;
import model.User;
import service.IUserService;
import java.util.List;

public class UserServiceImpl implements IUserService {

	IUserDAO userDAO = new UserDAOImpl();

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
	public User login(String email, String password) {
		User user = this.findByEmail(email);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User findById(int id) {
		return userDAO.findById(id);
	}

	@Override
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public boolean register(String name, String email, String password, String code) {
		if (userDAO.checkExistEmail(email))
			return false;
		userDAO.insert(new User(name, User.Role.USER, email, password, code, User.Status.INACTIVE));
		return true;
	}

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public List<User> search(String keyword) {
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}

	@Override
	public boolean checkExistPhone(String phoneNumber) {
		return userDAO.checkExistPhone(phoneNumber);
	}
}
