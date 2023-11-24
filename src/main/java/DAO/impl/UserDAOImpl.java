package DAO.impl;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import DAO.IUserDAO;
import models.User;
import utils.HibernateUtility;

public class UserDAOImpl implements IUserDAO {

	@Override
	public User findOne(int id) {
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			return session.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public User findOne(String email) {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			String hql = "FROM User WHERE email = :email";
			return session.createQuery(hql, User.class).setParameter("email", email).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void insert(User user) {
		Transaction transaction = null;
		Session session = HibernateUtility.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void update(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void delete(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
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

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<User> findAll() {
		Transaction transaction = null;
		List<User> listOfUser = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			listOfUser = session.createQuery("from User").getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfUser;
	}
}
