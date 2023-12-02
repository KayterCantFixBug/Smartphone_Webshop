package controller.Admin.Order;

import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(urlPatterns = {"/views/admin/addToOrder", "/addToOrder"})
public class AddToOrderServlet extends HttpServlet {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();
    private ProductServiceImpl productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try{
            addToOrder(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String url = request.getRequestURL().toString();
        if (url.contains("addToOrder")) {
            request.getRequestDispatcher("/views/admin/cart.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/home.jsp").forward(request, response);
        }
    }
    private void addToOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        try {
            // insert new order
            int product_id = Integer.parseInt(request.getParameter("product_id"));
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String Date = currentDate.format(formatter);
            String user_email = request.getParameter("email");
            User user = userService.findByEmail(user_email);
            Order order = new Order(user, Date);
            orderService.insert(order);

            // insert new orderDetail
            Product product = productService.findById(Product.class, product_id);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);

            // check if exist product, quantity+1
            int quantity = order.getQuantityOrderDetail(orderDetail);
            if (quantity > 1)
                quantity += 1;
            else {
                try {
                    if (quantity < 0) {
                        quantity = 1;
                    }
                } catch (NumberFormatException nfe) {
                    quantity = 1;
                }
            }
            orderDetail.setQuantity(quantity);


//            Product product = productService.findById(Product.class, product_id);
//            OrderDetail orderDetail = new OrderDetail(product, quantity);
//            orderDetailService.insert(orderDetail);
//
//
//
//            if (quantity > 0) {
//                cart.addIteam(lineItem);
//            } else if (quantity == 0) {
//                cart.removeItem(lineItem);
//            }
//
//            session.setAttribute("cart", cart);
//            System.out.println("after	:" + cart.getQuantityItem(lineItem));
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        url = "/homework/week7/ex7-2/cart.jsp";
//
//        response.sendRedirect("printListOrder");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
