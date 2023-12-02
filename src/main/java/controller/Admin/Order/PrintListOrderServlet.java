package controller.Admin.Order;

import model.Brand;
import model.Order;
import service.impl.BrandServiceImpl;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/views/admin/printListOrder","/printListOrder"})
public class PrintListOrderServlet extends HttpServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try{
            printListOrder(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURL().toString();
        List<Order> listOrder = orderService.getAll(Order.class);
        request.setAttribute("listOrder", listOrder);
        if (url.contains("printListOrder")) {
            request.getRequestDispatcher("/views/admin/order-list.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void printListOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Order> listOrder = orderService.getAll(Order.class);
        request.setAttribute("listOrder", listOrder);
        String url= "/views/admin/order-list.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }
}
