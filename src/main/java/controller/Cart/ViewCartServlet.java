package controller.Cart;

import model.Cart;
import model.LineItem;
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
import java.util.List;


@WebServlet(urlPatterns = {"/views/viewCart", "/viewCart"})
public class ViewCartServlet extends HttpServlet {
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
            viewCart(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("viewCart")) {
            request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void viewCart(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        try {
            // Check if user login or not
            HttpSession session = request.getSession();
            User user_login = (User) session.getAttribute("account");
            // User do not login
            if (user_login.getEmail() == null) {
                String url= "/views/admin/cart.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
            // User logged in
            else{
                // find cart of user
                User user = userService.findByEmail(user_login.getEmail());
                Cart cart = cartService.findByUser(user);
                List<LineItem> listLineItems = cartService.getAllLineItem();
                request.setAttribute("listLineItems", listLineItems);
                String url= "/views/cart.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
