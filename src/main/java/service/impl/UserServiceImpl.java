package service.impl;

import DAO.IUserDAO;
import DAO.impl.UserDAOImpl;
import model.User;
import service.IBaseService;
import service.IUserService;

import java.util.List;

public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

	UserDAOImpl userDAO = new UserDAOImpl();
	@Override
	public User login(String username, String password) {
		return null;
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
	public boolean checkExistEmail(String email) {
		return userDAO.checkExistEmail(email);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return false;
	}


}
