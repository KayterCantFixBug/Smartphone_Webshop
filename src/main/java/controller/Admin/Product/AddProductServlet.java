package controller.Admin.Product;

import model.Brand;
import model.Product;

import service.impl.BrandServiceImpl;
import service.impl.ProductServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/views/admin/addProduct", "/addProduct"})
public class AddProductServlet extends HttpServlet {
    private ProductServiceImpl productService = new ProductServiceImpl();
    private BrandServiceImpl brandService = new BrandServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            insertProduct(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addProduct")) {
            request.getRequestDispatcher("/views/admin/product-form-new.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String brand_string = request.getParameter("brand_id");
        String price_string = request.getParameter("price");
        String storage_string = request.getParameter("storage");
        String ram_string = request.getParameter("ram");
        String os = request.getParameter("os");
        String description = request.getParameter("description");

        // String image = request.getParameter("image");

        if (name == null) name ="";
        int brandId = 1;
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
        System.out.println(brand.getName());
        Product product = new Product(name, brand, price, storage, ram, os, description);
        productService.insert(product);
        response.sendRedirect("printListProduct");
    }
}
