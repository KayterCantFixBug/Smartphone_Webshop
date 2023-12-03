package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.*;
import service.impl.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/addToCart", "/removeCart", "/updateCart", "/viewCart", "/checkout" })
@MultipartConfig
public class CartServlet extends HttpServlet {

	ProductServiceImpl productService = new ProductServiceImpl();
	UserServiceImpl userService = new UserServiceImpl();
	CartServiceImpl cartService = new CartServiceImpl();
	LineItemServiceImpl lineItemService = new LineItemServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("viewCart")) {
			try {
				viewCart(request, response);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
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
		if (url.contains("addToCart")) {
			addToCart(request, response);
		} else if (url.contains("updateCart")) {
			updateCart(request, response);
		} else if (url.contains("removeCart")) {
			removeCart(request, response);
		} else if (url.contains("viewCart")) {
			try {
				viewCart(request, response);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else if (url.contains("checkout")) {
			checkout(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	private void checkout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
		}
		//orderService.insert(order);
		request.getRequestDispatcher("/views/thankyou.jsp").forward(request, response);
//		HttpSession session = request.getSession();
//		Order order = (Order) session.getAttribute("order");
//		if (order == null) {
//			order = new Order();
//		}
//		int product_id = Integer.parseInt(request.getParameter("product_id"));
//		OrderDetail orderDetail = new OrderDetail();
//		orderDetail.setProduct((Product) productService.findById(Product.class, product_id));
//		order.addOrderDetail(orderDetail);
//		session.setAttribute("order", order);
//		response.sendRedirect("viewCart");
	}

	@SuppressWarnings("unchecked")
	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Get product by Id
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			Product product = (Product) productService.findById(Product.class, product_id);

			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			// Check if user login or not
			HttpSession session = request.getSession();
			User user_login = (User)session.getAttribute("account");

			//System.out.println("UserLogin_email = "+ user_login.getEmail());
			// User do not login
			if (user_login == null) {
				Cart cart = (Cart) session.getAttribute("cart");
				if (cart == null) {
					cart = new Cart();
				}

				int quantity = cart.getQuantityItem(lineItem);
				if (quantity >= 1){
					quantity += 1;
					cart.updateLineItem(lineItem, quantity);
				}
				else if (quantity < 0) {
					quantity = 1;
					lineItem.setQuantity(quantity);
					cart.addLineItem(lineItem);
				}

				session.setAttribute("cart", cart);
			}
			// User logged in
			else{
				// find cart of user
				User user = userService.findByEmail(user_login.getEmail());
				Cart cart = cartService.findByUser(user);
				if (cart == null){
					cart = new Cart(user);
					cartService.insert(cart);
				}
				int quantity = 1;
				LineItem lineItem_temp = lineItemService.findLineItemByProduct(product_id);
				//System.out.println("Line item temp" + lineItem_temp.getQuantity());
				if (lineItem_temp == null){
					quantity = 1;
					lineItem.setQuantity(quantity);
					lineItem.setCart(cart);
					lineItemService.insert(lineItem);
				}
				else {
					quantity = lineItem_temp.getQuantity() + 1;
					lineItem_temp.setQuantity(quantity);
					lineItemService.update(lineItem_temp);
				}
			}
			response.sendRedirect("viewCart");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void updateCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Get product by Id
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			Product product = (Product) productService.findById(Product.class, product_id);
			String quantityString = request.getParameter("quantity");
			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			int quantity;
			try {
				quantity = Integer.parseInt(quantityString);
				if (quantity < 0) {
					quantity = 1;
				}
			} catch (NumberFormatException nfe) {
				quantity = 1;
			}
			// if the user enters a negative or invalid quanity,
			// the quantity is automatically resit to 1.

			lineItem.setQuantity(quantity);
			// Check if user login or not
			HttpSession session = request.getSession();
			User user_login = (User) session.getAttribute("account");
			// User do not login
			if (user_login == null) {
				Cart cart = (Cart) session.getAttribute("cart");

				if (quantity > 0) {
					cart.updateLineItem(lineItem, quantity);
				} else if (quantity == 0) {
					cart.removeLineItem(lineItem);
				}
				session.setAttribute("cart", cart);
			}
			// User logged in
			else{
				if (quantity > 0) {
					lineItemService.update(lineItem);
				} else if (quantity == 0) {
					lineItemService.delete(LineItem.class, lineItem.getId());
				}
			}
			response.sendRedirect("viewCart");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void removeCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Get product by Id
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			Product product = (Product) productService.findById(Product.class, product_id);
			LineItem lineItem = new LineItem();
			lineItem.setProduct(product);
			// Check if user login or not
			HttpSession session = request.getSession();
			User user_login = (User) session.getAttribute("account");
			// User do not login
			if (user_login == null) {
				Cart cart = (Cart) session.getAttribute("cart");
				if (cart == null) {
					cart = new Cart();
				}
				cart.removeLineItem(lineItem);
				session.setAttribute("cart", cart);
//                url = "/homework/week7/ex7-2/cart.jsp";
			}
			// User logged in
			else {
				lineItemService.delete(LineItem.class, lineItem.getId());
			}
			response.sendRedirect("viewCart");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void viewCart(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			// Check if user login or not
			HttpSession session = request.getSession();
			User user_login = (User) session.getAttribute("account");
			// User do not login
			if (user_login == null) {
				String url= "/views/cart.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
			// User logged in
			else{
				// find cart of user
				User user = userService.findByEmail(user_login.getEmail());
				Cart cart = cartService.findByUser(user);
				List<LineItem> lineItems = cartService.getAllLineItem(cart.getId());
//				for (int i = 0;  i < lineItems.size(); i++)
//					System.out.println("LineitemID=" + lineItems.get(i).getId());

				request.setAttribute("lineItems", lineItems);
				String url= "/views/cart.jsp";
				request.getRequestDispatcher(url).forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
