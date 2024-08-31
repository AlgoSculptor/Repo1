package cn.shivam.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import cn.shivam.connection.DBCon;
import cn.shivam.dao.OrderDao;

//@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CancelOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            if (id != null) {
                OrderDao orderDao = new OrderDao(DBCon.getConnection());
                orderDao.cancelOrder(Integer.parseInt(id));
            }
            
            // Determine the origin and redirect accordingly
            String origin = request.getParameter("origin");
            if ("admin".equals(origin)) {
                response.sendRedirect("admin-manage-orders");
            } else {
                response.sendRedirect("order.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
