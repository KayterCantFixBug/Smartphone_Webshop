package controller.Admin.OrderDetail;

import model.OrderDetail;
import service.impl.OrderDetailServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteOrderDetail", "/views/admin/deleteOrderDetail"})
public class DeleteOrderDetailServlet extends HttpServlet {
    private OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            deleteOrderDetail(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("deleteOrderDetail")) {
            request.getRequestDispatcher("/views/admin/order-detail-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void deleteOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        orderDetailService.delete(OrderDetail.class,id);
        response.sendRedirect("printListOrderDetail");
    }
}
