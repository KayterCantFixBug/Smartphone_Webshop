package DAO;

import model.User;

import java.util.List;

public interface IUserDAO {

	User findByEmail(String email);
	boolean checkExistEmail(String email);
}
