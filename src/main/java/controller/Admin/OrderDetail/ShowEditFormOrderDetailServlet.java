package controller.Admin.OrderDetail;

import service.impl.OrderDetailServiceImpl;
import service.impl.OrderServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/views/admin/showEditFormOrderDetail", "/showEditFormOrderDetail"})
public class ShowEditFormOrderDetailServlet extends HttpServlet {
    private OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            showEditForm(request, response);
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
        if (url.contains("showEditFormOrderDetail")) {
            request.getRequestDispatcher("/views/admin/order-detail-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/order-detail-form.jsp");
        dispatcher.forward(request, response);
    }
}
