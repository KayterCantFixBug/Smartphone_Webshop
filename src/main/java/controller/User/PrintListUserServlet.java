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
import java.util.List;

@WebServlet("/printListUser")
public class PrintListUserServlet extends HttpServlet {
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
            printListUser(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void printListUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<User> listUser = userDao.getAll();
        request.setAttribute("listUser", listUser);
        String url= "user-list.jsp";
        request.getRequestDispatcher("user-list.jsp").forward(request, response);
    }
}
