package controller.User;


import DAO.impl.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
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
            updateUser(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String phoneNumber = request.getParameter("phoneNumber");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        Date birthdate = (Date)new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthdate"));
        String image = request.getParameter("image");
        User.Gender gender = User.Gender.valueOf(request.getParameter("gender"));
        String country = request.getParameter("country");
        User user = new User(id, phoneNumber, name, password, birthdate, image,gender, country);
        userDao.update(user);
        response.sendRedirect("printListUser");
    }

}
