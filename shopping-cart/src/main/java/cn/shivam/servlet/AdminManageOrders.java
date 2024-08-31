package cn.shivam.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.OrderDao;
import cn.shivam.dao.ProductDao;
import cn.shivam.dao.UserDao;
import cn.shivam.model.Order;
import cn.shivam.model.Product;
import cn.shivam.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AdminManageOrders extends HttpServlet {
    private OrderDao orderDao;

    public void init() {
        try {
			orderDao = new OrderDao(DBCon.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the user is logged in and is an admin
        User auth = (User) request.getSession().getAttribute("auth");
       
        // Retrieve all orders
        List<Order> orders = orderDao.getAllOrders();
        request.setAttribute("orders", orders);

        // Forward to admin manage orders view
        request.getRequestDispatcher("admin_manage_orders.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle post requests, such as updating order status or canceling an order
    }
}