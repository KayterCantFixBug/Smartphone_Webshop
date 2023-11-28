package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.User;
import service.IUserService;
import service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(urlPatterns = { "/profile", "/editprofile" })
public class UserController extends HttpServlet {
	IUserService userService = new UserServiceImpl();
	public byte[] img = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("account");
		String url = request.getRequestURL().toString();
		if (url.contains("editprofile")) {
			User retrievedUser = userService.findByEmail(user.getEmail());
			if (retrievedUser.getImage() != null) {
				img = retrievedUser.getImage();
			}
			request.setAttribute("profileUser", retrievedUser);
			request.getRequestDispatcher("/views/home/editprofile.jsp").forward(request, response);
		} else if (url.contains("profile")) {
			request.getRequestDispatcher("/views/home/profile.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		String email = request.getParameter("email");
		User user = userService.findByEmail(email);
		if (url.contains("editprofile")) {
			Part filePart = request.getPart("image");
			if (filePart != null) {
				InputStream inputStream = filePart.getInputStream();
				byte[] imageBytes = inputStream.readAllBytes();
				user.setImage(imageBytes);
				userService.update(user);
				String base64Image = Base64.getEncoder().encodeToString(user.getImage());
				String imageData = "data:image/png;base64," + base64Image;
				request.setAttribute("imageData", imageData);
				request.setAttribute("profileUser", user);
				request.getRequestDispatcher("/views/home/editprofile.jsp").forward(request, response);
			}
		}
	}
}
