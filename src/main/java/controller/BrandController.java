package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import model.User.Gender;
import model.User.Role;
import model.User.Status;
import service.impl.UserServiceImpl;
import utility.Upload;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/addBrand", "/updateBrand", "/deleteBrand", "/listBrand" })
@MultipartConfig
public class BrandController extends HttpServlet {

	UserServiceImpl userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String url = request.getRequestURL().toString();

	}

	protected void addBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void updateBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void deleteBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
