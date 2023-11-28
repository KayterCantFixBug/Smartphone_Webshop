package controller.Admin.Product;

import service.IUserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddProductServlet extends HttpServlet {
    private IUserService userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getServletPath();

        try{
            insertProduct(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addUser")) {
            request.getRequestDispatcher("/views/admin/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.sendRedirect("register");
    }
}
