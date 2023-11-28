package controller.Admin.User;

import model.User;
import service.IUserService;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/views/admin/showEditFormUser", "/showEditFormUser"})
public class ShowEditFormUserServlet extends HttpServlet {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            showEditForm(request, response);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String url = request.getRequestURL().toString();
        if (url.contains("showEditFormUser")) {
            request.getRequestDispatcher("/views/admin/user-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(User.class, id);
        if (user.getGender() == null)
            user.setGender(User.Gender.UNKNOWN);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user-form.jsp");
        dispatcher.forward(request, response);
    }
}
