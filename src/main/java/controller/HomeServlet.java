package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import utility.Constant;
import utility.Email;
import model.User;
import model.User.Status;
import model.User.Role;

import service.IUserService;
import service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/verify", "/resetpassword", "/register", "/login", "/forgotpassword", "/logout" })
public class HomeServlet extends HttpServlet {

	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String url = request.getRequestURL().toString();
		if (url.contains("register")) {
			request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
		} else if (url.contains("login")) {
			getLogin(request, response);
		} else if (url.contains("logout")) {
			logout(request, response);
		} else if (url.contains("forgotpassword")) {
			request.getRequestDispatcher("/views/home/forgotpassword.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/home.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String url = request.getRequestURL().toString();
		if (url.contains("register")) {
			register(request, response);
		} else if (url.contains("verify")) {
			verify(request, response);
		} else if (url.contains("login")) {
			postLogin(request, response);
		} else if (url.contains("forgotpassword")) {
			forgotpassword(request, response);
		} else if (url.contains("resetpassword")) {
			resetpassword(request, response);
		}
	}

	private void forgotpassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String alertMsg = "";
		if (!userService.checkExistEmail(email)) {
			alertMsg = "This email has not been registered.";
			request.setAttribute("error", alertMsg);
			request.getRequestDispatcher("/views/home/forgotpassword.jsp").forward(request, response);
		} else {
			Email sm = new Email();
			User user = userService.findByEmail(email);
			String code = sm.getRandom();
			user.setCode(code);
			userService.update(user);
			String title = "TechGadget - Reset Password";
			String text = "Your code is: " + user.getCode();
			boolean test = sm.sendEmail(user.getEmail(), title, text);
			if (test) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				request.getRequestDispatcher("/views/home/resetpassword.jsp").forward(request, response);
			} else {
				PrintWriter out = response.getWriter();
				out.println("Error while sending the email!");
			}
		}
	}

	private void resetpassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String code = request.getParameter("authcode");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		if (code.equals(user.getCode())) {
			if (!password.equals(confirmPassword)) {
				request.setAttribute("error", "The confirmation password does not match.");
			} else {
				user.setPassword(password);
				userService.update(user);
				request.setAttribute("message", "Password changed successfully!");
			}
		} else {
			request.setAttribute("error", "Invalid code, please double-check.");
		}
		request.getRequestDispatcher("/views/home/resetpassword.jsp").forward(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("account");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
	}

	private void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("account") != null) {
			waiting(request, response);
			return;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("email")) {
					session = request.getSession(true);
					String email = cookie.getValue();
					session.setAttribute("email", email);
					User user = userService.findByEmail(email);
					session.setAttribute("account", user);
					waiting(request, response);
					return;
				}
			}
		}
		request.getRequestDispatcher("/views/home/login.jsp").forward(request, response);
	}

	private void postLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean isRememberMe = false;
		if (request.getParameter("remember") != null) {
			isRememberMe = true;
		}
		String alertMsg = "";
		if (email.isEmpty() || password.isEmpty()) {
			alertMsg = "Invalid email or password.";
			request.setAttribute("error", alertMsg);
			request.getRequestDispatcher("/views/home/login.jsp").forward(request, response);
			return;
		}
		User user = userService.login(email, password);
		if (user != null) {
			if (user.getStatus() == Status.ACTIVE) {
				HttpSession session = request.getSession(true);
				session.setAttribute("account", user);
				if (isRememberMe) {
					saveRemeberMe(response, email);
				}
				waiting(request, response);
			} else {
				alertMsg = "Account has been locked. Please contact the Admin.";
				request.setAttribute("error", alertMsg);
				request.getRequestDispatcher("/views/home/login.jsp").forward(request, response);
			}
		} else {
			alertMsg = "Invalid email or password.";
			request.setAttribute("error", alertMsg);
			request.getRequestDispatcher("/views/home/login.jsp").forward(request, response);
		}
	}

	private void waiting(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("account");
		User user = userService.findByEmail(u.getEmail());
		if (user.getImage() != null) {
			byte[] imageBytes = user.getImage();
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			String imageData = "data:image/png;base64," + base64Image;
			session.setAttribute("imageData", imageData);
		}
		if (session != null && session.getAttribute("account") != null) {
			if (u.getRole() == Role.ADMIN) {
				request.getRequestDispatcher("/views/admin/home.jsp").forward(request, response);
			} else if (u.getRole() == Role.USER) {
				request.getRequestDispatcher("/views/home.jsp").forward(request, response);
			}
		} else {
			getLogin(request, response);
		}
	}

	private void saveRemeberMe(HttpServletResponse response, String email) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, email);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
	}

	private void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		User retrievedUser = userService.findByEmail(user.getEmail());
		String code = request.getParameter("authcode");
		if (code.equals(retrievedUser.getCode())) {
			retrievedUser.setStatus(Status.ACTIVE);
			userService.update(retrievedUser);
			request.setAttribute("message", "Account successfully activated!");
		} else {
			request.setAttribute("error", "Invalid activation code, please double-check.");
		}
		request.getRequestDispatcher("/views/home/verify.jsp").forward(request, response);
	}

	protected void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String alertMsg = "";
		if (userService.checkExistEmail(email)) {
			alertMsg = "The email already exists!";
			request.setAttribute("error", alertMsg);
			request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
		} else {
			if (!password.equals(confirmPassword)) {
				alertMsg = "The confirmation password does not match.";
				request.setAttribute("error", alertMsg);
				request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
			} else {
				Email sm = new Email();
				String code = sm.getRandom();
				User user = new User(name, email, code);
				String title = "TechGadget - Activate Account";
				String text = "Your code is: " + user.getCode();
				boolean test = sm.sendEmail(user.getEmail(), title, text);
				if (test) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					boolean isSuccess = userService.register(name, email, password, code);
					if (isSuccess) {
						request.getRequestDispatcher("/views/home/verify.jsp").forward(request, response);
					} else {
						alertMsg = "System error!";
						request.setAttribute("error", alertMsg);
						request.getRequestDispatcher("/views/home/register.jsp").forward(request, response);
					}
				} else {
					PrintWriter out = response.getWriter();
					out.println("Error while sending the email!");
				}
			}
		}
	}
}
