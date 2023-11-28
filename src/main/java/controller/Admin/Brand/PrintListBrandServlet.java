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
import java.util.List;

@WebServlet(urlPatterns = {"/views/admin/printListBrand","/printListBrand"})
public class PrintListBrandServlet extends HttpServlet {
    private BrandServiceImpl brandService = new BrandServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            printListBrand(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        List<Brand> listBrand = brandService.getAll(Brand.class);
        request.setAttribute("listBrand", listBrand);
        if (url.contains("printListBrand")) {
            request.getRequestDispatcher("/views/admin/brand-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void printListBrand(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Brand> listBrand = brandService.getAll(Brand.class);
        request.setAttribute("listBrand", listBrand);
        String url= "/views/admin/brand-list.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
