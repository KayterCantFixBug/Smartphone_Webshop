package controller.User;


import DAO.impl.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addUser")
public class AddUserServlet  extends HttpServlet {

    private UserDAOImpl userDao;

    public void init(){
        userDao = new UserDAOImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getServletPath();

        try{
            insertUser(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("register")) {
            request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        response.sendRedirect("register");
    }

}
