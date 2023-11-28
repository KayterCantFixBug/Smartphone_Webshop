package DAO.impl;

import DAO.IUserDAO;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtility;

import java.util.List;

public class UserDAOImpl extends BaseDAOImpl implements IUserDAO {


	@Override
	public User findByEmail(String email) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String hql = "FROM User WHERE email = :email";
			return session.createQuery(hql, User.class).setParameter("email", email).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String hql = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email";
			boolean exists = session.createQuery(hql, Boolean.class).setParameter("email", email).uniqueResult();
			return exists;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

//	@Override
//	public List<User> getAll() {
//		Transaction transaction = null;
//		List<User> listOfUser = null;
//		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
//			transaction = session.beginTransaction();
//			listOfUser = session.createQuery("from User").getResultList();
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace();
//		}
//		return listOfUser;
//	}
}
