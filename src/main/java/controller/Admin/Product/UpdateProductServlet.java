package controller.Admin.Product;

import model.Brand;
import model.Product;
import model.User;
import service.IProductService;
import service.IUserService;
import service.impl.BaseServiceImpl;
import service.impl.BrandServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import utility.HibernateUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet(urlPatterns = {"/views/admin/updateProduct", "/updateProduct"})
public class UpdateProductServlet extends HttpServlet {
    private ProductServiceImpl productService = new ProductServiceImpl();
    private BrandServiceImpl brandService = new BrandServiceImpl();
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
        if (url.contains("updateProduct")) {
            request.getRequestDispatcher("/views/admin/product-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String brand_string = request.getParameter("brand");
        String price_string = request.getParameter("price");
        String storage_string = request.getParameter("storage");
        String ram_string = request.getParameter("ram");
        String os = request.getParameter("os");
        String description = request.getParameter("description");

        // String image = request.getParameter("image");

        if (name.equals(null)) name ="";
        int brandId = 1;
//        int brand_length = brandService.getAll(Brand.class).size();
//        if (Integer.parseInt(brand_string) > brand_length)
//            brandId = 1;
//        else
            if (brand_string != null && !brand_string.isEmpty()) {
            try {
                brandId = Integer.parseInt(brand_string);
            } catch (NumberFormatException e) {
                brandId = 1;
            }
        }

        double price = 0;
        if (price_string != null && !price_string.isEmpty()) {
            try {
                price = Double.parseDouble(price_string);
            } catch (NumberFormatException e) {
                price = 0;
            }
        }
        double storage = 0;
        if (storage_string != null && !storage_string.isEmpty()) {
            try {
                storage = Double.parseDouble(storage_string);
            } catch (NumberFormatException e) {
                storage = 0;
            }
        }
        double ram = 0;
        if (ram_string != null && !ram_string.isEmpty()) {
            try {
                ram = Double.parseDouble(ram_string);
            } catch (NumberFormatException e) {
                ram = 0;
            }
        }
        if (os == null) os = "";
        if (description == null) description ="";

        Brand brand = new Brand();
        brand = brandService.findById(Brand.class, brandId);

        Product product = new Product(id, name, brand, price, storage, ram, os, description);
        productService.update(product);
        response.sendRedirect("printListProduct");
    }
}
