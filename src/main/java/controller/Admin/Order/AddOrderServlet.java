package controller.Admin.Order;

import model.User;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Order;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/views/admin/addOrder", "/addOrder"})
public class AddOrderServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            insertOrder(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addOrder")) {
            request.getRequestDispatcher("/views/admin/order-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {


        response.sendRedirect("printListOrder");
    }
}
