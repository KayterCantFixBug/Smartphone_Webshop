package controller.Admin.Cart;

import model.Cart;
import model.LineItem;
import model.Product;
import model.User;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/views/admin/removeCart", "/removeCart"})
public class RemoveCartServlet extends HttpServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private LineItemServiceImpl lineItemService = new LineItemServiceImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            removeCart(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("removeCart")) {
            request.getRequestDispatcher("/views/admin/cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void removeCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        try {
            // Get product by Id
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            Product product = productService.findById(Product.class, product_id);
            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);


            // Check if user login or not
            HttpSession session = request.getSession();
            User user_login = (User) session.getAttribute("account");
            // User do not login
            if (user_login.getEmail() == null) {
                Cart cart = (Cart) session.getAttribute("cart");
                if (cart == null) {
                    cart = new Cart();
                }
                cart.removeLineItem(lineItem);
                session.setAttribute("cart", cart);
//                url = "/homework/week7/ex7-2/cart.jsp";
            }
            // User logged in
            else {
                lineItemService.delete(LineItem.class, lineItem.getId());
            }
            response.sendRedirect("viewCartServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
