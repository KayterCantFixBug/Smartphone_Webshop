package controller.Admin.User;

import DAO.impl.UserDAOImpl;
import model.User;
import service.IUserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/views/admin/printListUser","/printListUser"})
public class PrintListUserServlet extends HttpServlet {


    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            printListUser(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
//        response.setCharacterEncoding("UTF-8");
//
//        String url = request.getRequestURL().toString();
//        if (url.contains("printListUser")) {
//            request.getRequestDispatcher("/views/admin/user-list.jsp").forward(request, response);
//        } else {
//            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
//        }
    }
    private void printListUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<User> listUser = userService.getAll();
        request.setAttribute("listUser", listUser);
        System.out.println("ok");
        String url= "/views/admin/user-list.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
