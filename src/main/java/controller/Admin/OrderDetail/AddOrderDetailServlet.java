package controller.Admin.OrderDetail;


import model.OrderDetail;
import model.Product;
import service.impl.OrderDetailServiceImpl;
import service.impl.ProductServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/views/admin/addOrderDetail", "/addOrderDetail"})
public class AddOrderDetailServlet {

    private OrderDetailServiceImpl orderDetailService = new OrderDetailServiceImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            insertOrderDetail(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addOrderDetail")) {
            request.getRequestDispatcher("/views/admin/order-detail-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void insertOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int quantity = 1;
        Product product = productService.findById(Product.class, product_id);
        OrderDetail orderDetail = new OrderDetail(product, quantity);
        orderDetailService.insert(orderDetail);
        response.sendRedirect("printListOrderDetail");
    }
}
