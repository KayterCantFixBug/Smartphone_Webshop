package controller.User;

import DAO.impl.UserDAOImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/showEditFormUser")
public class ShowEditFormServlet extends HttpServlet {
    private UserDAOImpl userDao;

    public void init(){
        userDao = new UserDAOImpl();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getServletPath();
        try{
            showEditForm(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDao.findById(id);
        String url = "user-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }
}
