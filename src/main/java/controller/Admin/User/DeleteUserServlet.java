package controller.Admin.User;


import model.User;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteUser", "/views/admin/deleteUser"})
public class DeleteUserServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getServletPath();
        try{
            deleteUser(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("deleteUser")) {
            request.getRequestDispatcher("/views/admin/user-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delete(User.class, id);
        response.sendRedirect("printListUser");
    }

}
