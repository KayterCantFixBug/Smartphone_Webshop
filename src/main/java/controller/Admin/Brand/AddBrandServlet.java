package controller.Admin.Brand;

import model.Brand;
import service.impl.BrandServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/views/admin/addBrand", "/addBrand"})
public class AddBrandServlet  extends HttpServlet {

    private BrandServiceImpl brandService = new BrandServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            insertBrand(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addBrand")) {
            request.getRequestDispatcher("/views/admin/brand-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void insertBrand(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String country = request.getParameter("country");

        Brand brand = new Brand(name, country);
        brandService.insert(brand);
        response.sendRedirect("printListBrand");
    }

}
