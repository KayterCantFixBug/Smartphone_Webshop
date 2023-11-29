package controller.Admin.Product;

import model.Product;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/views/admin/printListProduct","/printListProduct"})
public class PrintListProductServlet extends HttpServlet {
    private ProductServiceImpl productService = new ProductServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            printListProduct(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        List<Product> listProduct = productService.getAll(Product.class);
        request.setAttribute("listProduct", listProduct);
        if (url.contains("printListProduct")) {
            request.getRequestDispatcher("/views/admin/product-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void printListProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        List<Product> listProduct = productService.getAll(Product.class);
        request.setAttribute("listProduct", listProduct);
        String url= "/views/admin/product-list.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
