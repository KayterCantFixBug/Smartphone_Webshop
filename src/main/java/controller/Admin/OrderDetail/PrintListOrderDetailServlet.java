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
import java.util.List;

@WebServlet(urlPatterns = {"/views/admin/printListOrderDetail","/printListOrderDetail"})
public class PrintListOrderDetailServlet extends HttpServlet {
    private OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            printListOrderDetail(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        List<OrderDetail> listOrderDetail = orderDetailService.getAll(OrderDetail.class);
        request.setAttribute("listOrderDetail", listOrderDetail);
        if (url.contains("printListOrderDetail")) {
            request.getRequestDispatcher("/views/admin/order-detail-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void printListOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<OrderDetail> listOrderDetail = orderDetailService.getAll(OrderDetail.class);
        request.setAttribute("listOrderDetail", listOrderDetail);
        String url= "/views/admin/order-detail-list.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
