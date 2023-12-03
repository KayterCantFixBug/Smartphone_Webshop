package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Order;
import model.OrderDetail;
import model.Product;
import service.impl.OrderDetailServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/addToCart", "/removeCart", "/updateCart", "/viewCart" })
@MultipartConfig
public class CartServlet extends HttpServlet {

	ProductServiceImpl productService = new ProductServiceImpl();
	UserServiceImpl userService = new UserServiceImpl();
	OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
		}
		String url = request.getRequestURL().toString();
		if (url.contains("viewCart")) {
			request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
		} else if (url.contains("addToCart")) {
			addToCart(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
		}
		if (url.contains("addToCart")) {
			addToCart(request, response);
		} else if (url.contains("updateCart")) {
			updateCart(request, response);
		} else if (url.contains("removeCart")) {
			removeCart(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct((Product) productService.findById(Product.class, product_id));
		order.addOrderDetail(orderDetail);
		session.setAttribute("order", order);
		response.sendRedirect("viewCart");
	}

	@SuppressWarnings("unchecked")
	private void updateCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct((Product) productService.findById(Product.class, product_id));
		orderDetail.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		order.addOrderDetail(orderDetail);
		session.setAttribute("order", order);
		response.sendRedirect("viewCart");
	}

	@SuppressWarnings("unchecked")
	private void removeCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		int product_id = Integer.parseInt(request.getParameter("product_id"));
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct((Product) productService.findById(Product.class, product_id));
		order.removeOrderDetail(orderDetail);
		session.setAttribute("order", order);
		response.sendRedirect("viewCart");
	}
}
