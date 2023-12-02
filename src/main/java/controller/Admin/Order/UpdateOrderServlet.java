package controller.Admin.Order;


import model.Order;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/views/admin/updateOrder","/updateOrder"})
public class UpdateOrderServlet extends HttpServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            updateOrder(request, response);
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
        if (url.contains("updateOrder")) {
            request.getRequestDispatcher("/views/admin/Order-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");

        if (address == null) address = "";
        if (phone == null) phone = "";
        if (note == null) note = "";


        Order order = new Order(id, address, phone, note);
        orderService.update(order);
        response.sendRedirect("printListOrder");
    }
}
