package DAO.impl;

import DAO.ICartDAO;
import model.Cart;
import model.LineItem;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateUtility;

import java.util.List;

public class CartDAOImpl extends BaseDAOImpl<Cart> implements ICartDAO {
    @Override
    public Cart findByUser(User user) {
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            String hql = "FROM Cart WHERE user.email =: user_email";
            return session.createQuery(hql, Cart.class).setParameter("user", user).uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LineItem> getAllLineItem() {
        Transaction transaction = null;
        List<LineItem> listOfLineItem = null;
        try (Session session = HibernateUtility.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM Cart JOIN LineItem WHERE Cart.id =: cart_id";
            listOfLineItem = session.createQuery(hql).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfLineItem;
    }


}
