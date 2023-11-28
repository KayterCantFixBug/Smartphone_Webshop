package controller.Admin.Product;

import model.Brand;
import model.User;
import service.IProductService;
import service.IUserService;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import utility.HibernateUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateProductServlet extends HttpServlet {
    private ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            updateProduct(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
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
        if (url.contains("updateUser")) {
            request.getRequestDispatcher("/views/admin/user-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String brandIdParameter = request.getParameter("brand_id");
        double price = Double.parseDouble(request.getParameter("price"));
        String storage = request.getParameter("storage");
        double ram = Double.parseDouble(request.getParameter("ram"));
        String os = request.getParameter("os");
        String description = request.getParameter("description");

        if (name.equals(null)) name ="";
        int brandId;
        if (brandIdParameter != null && !brandIdParameter.isEmpty()) {
            try {
                brandId = Integer.parseInt(brandIdParameter);
            } catch (NumberFormatException e) {
                brandId = 1;
            }

        if (password.equals(null)) password ="";
        if (birthdate_string.equals(""))
            birthdate = null;
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            birthdate = formatter.parse(birthdate_string);
        }
        if (email.equals(null)) email ="";

        User user = new User(id, phoneNumber, name, password, birthdate, email, status, gender);
        userService.update(user);
        response.sendRedirect("printListUser");
    }
}
