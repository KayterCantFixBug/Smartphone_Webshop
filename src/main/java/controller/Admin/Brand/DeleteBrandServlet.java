package controller.Admin.Brand;

import model.Brand;
import service.impl.BrandServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteBrand", "/views/admin/deleteBrand"})
public class DeleteBrandServlet  extends HttpServlet {
    private BrandServiceImpl brandService = new BrandServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String action = request.getServletPath();
        try{
            deleteBrand(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("deleteBrand")) {
            request.getRequestDispatcher("/views/admin/brand-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void deleteBrand(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        brandService.delete(Brand.class,id);
        response.sendRedirect("printListBrand");
    }
}
