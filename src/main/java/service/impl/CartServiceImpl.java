package service.impl;

import DAO.impl.BaseDAOImpl;
import DAO.impl.CartDAOImpl;
import DAO.impl.UserDAOImpl;
import model.Cart;
import model.LineItem;
import model.User;
import service.ICartService;

import java.util.List;

public class CartServiceImpl extends BaseDAOImpl<Cart> implements ICartService {
    CartDAOImpl cartDAO = new CartDAOImpl();

    @Override
    public List<LineItem> getAllLineItem() {
        return cartDAO.getAllLineItem();
    }

    public Cart findByUser(User user) {
        return cartDAO.findByUser(user);
    }

}
