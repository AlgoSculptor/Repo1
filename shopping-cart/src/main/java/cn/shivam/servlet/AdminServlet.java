package cn.shivam.servlet;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.OrderDao;
import cn.shivam.dao.UserDao;
import cn.shivam.model.Order;
import cn.shivam.model.User;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establish database connection
        Connection con = null;
        try {
            con = DBCon.getConnection();

            // Get order details
            OrderDao orderDao = new OrderDao(con);
            List<Order> orders = orderDao.userOrders();

            // Get user details
            UserDao userDao = new UserDao(con);
            List<User> users = userDao.getAllUsers();

            // Close the database connection
            con.close();

            // Set attributes to forward data to JSP
            request.setAttribute("orders", orders);
            request.setAttribute("users", users);

            // Forward to admin.jsp
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}