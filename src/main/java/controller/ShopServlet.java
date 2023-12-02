package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Product;
import service.impl.ProductServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/shop"})
@MultipartConfig
public class ShopServlet extends HttpServlet {
	ProductServiceImpl productService = new ProductServiceImpl();

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("shop")) {
			List<Product> listProduct = productService.filterProduct("", 1);
			int nop = (productService.getAll(Product.class).size() / 8) + 1;
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("numberOfPages", nop);
			request.getRequestDispatcher("/views/shop.jsp").forward(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("shop")) {
			List<Product> listProduct = productService.filterProduct(request.getParameter("search"),
					Integer.parseInt(request.getParameter("page")));
			int nop = (productService.getAll(Product.class).size() / 8) + 1;
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("numberOfPages", nop);
			request.getRequestDispatcher("/views/shop.jsp").forward(request, response);
		}
	}
}