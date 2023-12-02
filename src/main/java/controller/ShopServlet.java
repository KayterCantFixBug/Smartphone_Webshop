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
@WebServlet(urlPatterns = { "/shop" })
@MultipartConfig
public class ShopServlet extends HttpServlet {
	ProductServiceImpl productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("shop")) {
			int currentPage = 1;
			String search = "";
			List<Product> listProduct = productService.filterProduct(search, currentPage);
			int nop = 5;
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("numberOfPages", nop);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("search", search);
			request.getRequestDispatcher("/views/shop.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("shop")) {
			int currentPage = Integer.parseInt(request.getParameter("page"));
			String search = request.getParameter("search");
			List<Product> listProduct = productService.filterProduct(search, currentPage);
			int nop = 5;
			request.setAttribute("listProduct", listProduct);
			request.setAttribute("numberOfPages", nop);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("search", search);
			request.getRequestDispatcher("/views/shop.jsp").forward(request, response);
		}
	}
}