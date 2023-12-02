package service;

import model.Cart;
import model.LineItem;
import model.User;

import java.util.List;

public interface ICartService {
    List<LineItem> getAllLineItem();
    Cart findByUser(User user);
}
