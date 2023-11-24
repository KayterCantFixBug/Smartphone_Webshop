package controller;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utility.Email;
import model.User;
import model.User.Status;
import service.IUserService;
import service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/views/home/register", "/views/home/verify", "/register" })
public class HomeServlet extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("register")) {
            request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("register")) {
            register(request, response);
        } else if (url.contains("verify")) {
            verify(request, response);
        }
    }

    private void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        User retrievedUser = userService.findByEmail(user.getEmail());
        String code = request.getParameter("authcode");
        if (code.equals(retrievedUser.getCode())) {
            retrievedUser.setStatus(Status.ACTIVE);
            userService.update(retrievedUser);
            request.setAttribute("ok", "true");
            request.setAttribute("message", "Account successfully activated!");
        } else {
            request.setAttribute("ok", "false");
            request.setAttribute("message", "Invalid activation code, please double-check.");
        }
        request.getRequestDispatcher("/views/home/verify.jsp").forward(request, response);
    }

    protected void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String alertMsg = "";
        if (userService.checkExistEmail(email)) {
            alertMsg = "The email already exists!";
            request.setAttribute("error", alertMsg);
            getServletContext().getRequestDispatcher("/views/home/register.jsp").forward(request, response);
        } else {
            if (!password.equals(confirmPassword)) {
                alertMsg = "The confirmation password does not match.";
                request.setAttribute("error", alertMsg);
                request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
            } else {
                Email sm = new Email();
                String code = sm.getRandom();
                User user = new User(name, email, code);
                boolean test = sm.sendEmail(user);
                if (test) {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", user);
                    boolean isSuccess = userService.register(name, email, password, code);
                    if (isSuccess) {
                        request.getRequestDispatcher("/views/home/verify.jsp").forward(request, response);
                    } else {
                        alertMsg = "System error!";
                        request.setAttribute("error", alertMsg);
                        request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
                    }
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("Error while sending the email!");
                }
            }
        }
    }
}
