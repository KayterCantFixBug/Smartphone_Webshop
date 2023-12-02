package controller.Cart;

import model.Cart;
import model.LineItem;
import model.Product;
import model.User;
import service.impl.LineItemServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/views/updateCart", "/updateCart"})
public class UpdateCartServlet extends HttpServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private LineItemServiceImpl lineItemService = new LineItemServiceImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            updateCart(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("updateCart")) {
            request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void updateCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        try {
            // Get product by Id
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Product product = (Product) productService.findById(Product.class, product_id);
            String quantityString = request.getParameter("quantity");
            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }
            // if the user enters a negative or invalid quanity,
            // the quantity is automatically resit to 1.

            lineItem.setQuantity(quantity);
            // Check if user login or not
            HttpSession session = request.getSession();
            User user_login = (User) session.getAttribute("account");
            // User do not login
            if (user_login.getEmail() == null) {
                Cart cart = (Cart) session.getAttribute("cart");

                if (quantity > 0) {
                    cart.updateLineItem(lineItem, quantity);
                } else if (quantity == 0) {
                    cart.removeLineItem(lineItem);
                }
                session.setAttribute("cart", cart);
            }
            // User logged in
            else{
                if (quantity > 0) {
                    lineItemService.update(lineItem);
                } else if (quantity == 0) {
                    lineItemService.delete(LineItem.class, lineItem.getId());
                }
            }
            response.sendRedirect("viewCartServlet");
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
