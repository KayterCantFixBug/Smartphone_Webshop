package controller.Admin.Cart;

import model.*;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet(urlPatterns = {"/views/admin/addToCart", "/addToCart"})
public class AddToCartServlet extends HttpServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();
    private CartServiceImpl cartService = new CartServiceImpl();
    private LineItemServiceImpl lineItemService = new LineItemServiceImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            addToCart(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addToCart")) {
            request.getRequestDispatcher("/views/admin/cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        try {
            // Get product by Id
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Product product = productService.findById(Product.class, product_id);

            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            // Check if user login or not
            String email = request.getParameter("email");
            // User do not login
            if (email == null) {
                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }

                int quantity = cart.getQuantityItem(lineItem);
                if (quantity > 1)
                    quantity += 1;
                else if (quantity < 0) {
                    quantity = 1;
                }
                lineItem.setQuantity(quantity);

                cart.addLineItem(lineItem);
                session.setAttribute("cart", cart);
            }
            // User logged in
            else{
                // find cart of user
                User user = userService.findByEmail(email);
                Cart cart = cartService.findByUser(user);
                if (cart == null){
                    cart = new Cart(user);
                    cartService.insert(cart);
                }

                int quantity = cart.getQuantityItem(lineItem);
                if (quantity > 1)
                    quantity += 1;
                else if (quantity < 0) {
                    quantity = 1;
                }
                lineItem.setQuantity(quantity);
                lineItem.setCart(cart);
                lineItemService.insert(lineItem);
            }
            response.sendRedirect("viewCartServlet");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
