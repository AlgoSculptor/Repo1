package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.OrderDao;
import cn.shivam.model.Cart;
import cn.shivam.model.Order;
import cn.shivam.model.User;

//@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to the doPost method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            User auth = (User) request.getSession().getAttribute("auth");
            if (auth != null) {
                String productId = request.getParameter("id");
                String quantityStr = request.getParameter("quantity");
                if (quantityStr == null || quantityStr.isEmpty()) {
                    throw new NumberFormatException("Quantity is null or empty");
                }
                int productQuantity = Integer.parseInt(quantityStr);
                String deliveryAddress = request.getParameter("address");

                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setuId(auth.getId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatter.format(date));
                orderModel.setDeliveryAddress(deliveryAddress);

                OrderDao orderDao = new OrderDao(DBCon.getConnection());
                boolean result = orderDao.insertOrder(orderModel);

                if (result) {
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    if (cart_list != null) {
                        for (Cart c : cart_list) {
                            if (c.getId() == Integer.parseInt(productId)) {
                                cart_list.remove(cart_list.indexOf(c));
                                break;
                            }
                        }
                    }

                    // Redirect to the order.jsp page
                    response.sendRedirect("order.jsp");
                } else {
                    out.print("order failed");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.print("Invalid quantity format. Please enter a valid number.");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("Order failed. Please try again later.");
        }
    }
}
